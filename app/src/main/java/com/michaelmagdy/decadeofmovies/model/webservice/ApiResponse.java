package com.michaelmagdy.decadeofmovies.model.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {


    @Expose
    @SerializedName("stat")
    private String stat;
    @Expose
    @SerializedName("photos")
    private Photos photos;

    public String getStat() {
        return stat;
    }

    public Photos getPhotos() {
        return photos;
    }
}
