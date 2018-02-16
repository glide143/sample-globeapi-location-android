
package com.example.melchy.globle_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminalLocation {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("currentLocation")
    @Expose
    private CurrentLocation currentLocation;
    @SerializedName("locationRetrievalStatus")
    @Expose
    private String locationRetrievalStatus;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLocationRetrievalStatus() {
        return locationRetrievalStatus;
    }

    public void setLocationRetrievalStatus(String locationRetrievalStatus) {
        this.locationRetrievalStatus = locationRetrievalStatus;
    }

}
