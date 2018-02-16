
package com.example.melchy.globle_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentLocation {

    @SerializedName("accuracy")
    @Expose
    private Object accuracy;
    @SerializedName("altitude")
    @Expose
    private Object altitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("map_url")
    @Expose
    private String mapUrl;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Object getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Object accuracy) {
        this.accuracy = accuracy;
    }

    public Object getAltitude() {
        return altitude;
    }

    public void setAltitude(Object altitude) {
        this.altitude = altitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
