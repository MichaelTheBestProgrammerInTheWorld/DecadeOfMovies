package com.michaelmagdy.decadeofmovies.model.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    @Expose
    @SerializedName("isfamily")
    private int isfamily;
    @Expose
    @SerializedName("isfriend")
    private int isfriend;
    @Expose
    @SerializedName("ispublic")
    private int ispublic;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("farm")
    private int farm;
    @Expose
    @SerializedName("server")
    private String server;
    @Expose
    @SerializedName("secret")
    private String secret;
    @Expose
    @SerializedName("owner")
    private String owner;
    @Expose
    @SerializedName("id")
    private String id;

    public int getIsfamily() {
        return isfamily;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public int getIspublic() {
        return ispublic;
    }

    public String getTitle() {
        return title;
    }

    public int getFarm() {
        return farm;
    }

    public String getServer() {
        return server;
    }

    public String getSecret() {
        return secret;
    }

    public String getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }
}
