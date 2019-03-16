package com.example.bytecamp_raw.Model;

import com.google.firebase.database.Exclude;

/**
 * Created by venkat on 23/2/19.
 */
public class Location {
    public Location(String lat, String aLong) {
        Lat = lat;
        Long = aLong;
    }

    public Location() {
    }

    @Exclude
    public String getLat() {
        return Lat;
    }

    @Exclude
    public void setLat(String lat) {
        Lat = lat;
    }

    @Exclude
    public String getLong() {
        return Long;
    }

    @Exclude
    public void setLong(String aLong) {
        Long = aLong;
    }

    String Lat,Long;
}
