package com.michaelmagdy.decadeofmovies.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private List<Movie> movieList = new ArrayList<>();
    private InputStream inputStream;

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        showAllMovies();
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void showAllMovies(){

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());// Load JSON File From Asset Folder

            JSONArray moviesArray = jsonObject.getJSONArray("movies");// read Employee Array
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject jsonObjectMovies = moviesArray.getJSONObject(i);
                String title = jsonObjectMovies.getString("title");
                int year = jsonObjectMovies.getInt("year");
                int rating = jsonObjectMovies.getInt("rating");
                JSONArray cast = jsonObjectMovies.getJSONArray("cast");
                JSONArray genre = jsonObjectMovies.getJSONArray("genres");
                ArrayList<String> castList = new ArrayList<>();
                ArrayList<String> genreList = new ArrayList<>();
                for (int j=0;j<cast.length();j++){
                    castList.add(cast.get(j).toString());
                }
                for (int j=0;j<genre.length();j++){
                    genreList.add(genre.get(j).toString());
                }
                movieList.add(new Movie(rating,genreList,castList, year, title));

                if (movieList != null) {
                    moviesLiveData.postValue(movieList);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
