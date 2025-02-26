package com.abhik.community_resource_locator;

public class Location {

    private double latitude, longitude;

    public Location() {}  // Empty constructor for Firebase

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
