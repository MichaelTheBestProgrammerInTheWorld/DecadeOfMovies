package com.michaelmagdy.decadeofmovies.model;

import java.util.List;

public class Movie {

    private int rating;
    private List<String> genres;
    private List<String> cast;
    private int year;
    private String title;

    public Movie(int rating, List<String> genres, List<String> cast, int year, String title) {
        this.rating = rating;
        this.genres = genres;
        this.cast = cast;
        this.year = year;
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
