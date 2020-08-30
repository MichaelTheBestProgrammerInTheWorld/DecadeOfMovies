package com.michaelmagdy.decadeofmovies.model.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @Expose
    @SerializedName("photo")
    private List<Photo> photo;
    @Expose
    @SerializedName("total")
    private String total;
    @Expose
    @SerializedName("perpage")
    private int perpage;
    @Expose
    @SerializedName("pages")
    private int pages;
    @Expose
    @SerializedName("page")
    private int page;

    public List<Photo> getPhoto() {
        return photo;
    }

    public String getTotal() {
        return total;
    }

    public int getPerpage() {
        return perpage;
    }

    public int getPages() {
        return pages;
    }

    public int getPage() {
        return page;
    }
}
