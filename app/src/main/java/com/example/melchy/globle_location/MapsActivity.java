package com.example.melchy.globle_location;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ph.com.globe.connect.ApiException;
import ph.com.globe.connect.AsyncHandler;
import ph.com.globe.connect.HttpRequestException;
import ph.com.globe.connect.HttpResponse;
import ph.com.globe.connect.HttpResponseException;
import ph.com.globe.connect.Location;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location location;
    private String TAG = "MapActivityLog";
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        location = new Location(getString(R.string.ACCESS_TOKEN));
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Finding Subscriber");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    public void funLocate(View view){
        locationGet();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void locationGet(){
        if(isNetworkAvailable()){
            mProgressDialog.show();
            mProgressDialog.setMessage("Looking for your subscriber Please Wait....");
            try {
                location
                        .setAddress(getString(R.string.subscriberNumber))
                        .setRequestedAccuracy(10)
                        .getLocation(new AsyncHandler() {
                            @Override
                            public void response(HttpResponse response) throws HttpResponseException {
                                try {
                                    JSONObject json = new JSONObject(response.getJsonResponse().toString());
                                    getLocation getlocation = new getLocation();
                                    getlocation.execute(json.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

            }catch(ApiException | HttpRequestException | HttpResponseException e) {
                e.printStackTrace();
            }
        }else{
            mProgressDialog.dismiss();
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private class getLocation extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            Log.d(TAG,strings[0]);
            return strings[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            GlobeLocation globeLocation = gson.fromJson(s,GlobeLocation.class);
            try{
                double lati = Double.parseDouble(globeLocation.getTerminalLocationList()
                        .getTerminalLocation()
                        .getCurrentLocation()
                        .getLatitude()
                );
                double longti = Double.parseDouble(globeLocation.getTerminalLocationList()
                        .getTerminalLocation()
                        .getCurrentLocation()
                        .getLongitude()
                );
                LatLng result = new LatLng(lati,longti);
                mMap.addMarker(new MarkerOptions().position(result).title("Subscriber Location"));
                if (result != null)
                {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(result, 13));
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target((result))      // Sets the center of the map to location user
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    mProgressDialog.dismiss();
                }
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
            }
        }
    }

}
