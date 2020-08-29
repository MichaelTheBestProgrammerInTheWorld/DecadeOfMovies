package com.michaelmagdy.decadeofmovies.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Repository {

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private List<Movie> movieList = new ArrayList<>();
    private InputStream inputStream;
    private Set<Integer> setYears = new HashSet<Integer>();
    //search variables
    private int startingYear;
    private HashMap<Integer, Integer> yearsIndexHash = new HashMap<>();
    private List<Movie> filteredList = new ArrayList<>();

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        showAllMovies();
    }

    public List<Integer> getYearsList() {
        List<Integer> yearsList = new ArrayList<Integer>(setYears) ;
        Collections.sort(yearsList);
        return yearsList;
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
                setYears.add(year);
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
                //saving indicies for search
                if (i == 0){
                    startingYear = year;
                    yearsIndexHash.put(startingYear, i);
                } else if (year == startingYear+1){
                    startingYear = year;
                    yearsIndexHash.put(startingYear, i);
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


    public MutableLiveData<List<Movie>> filterMoviesByYear(int selectedYear){

        filteredList.clear();
        int startIndex = yearsIndexHash.get(selectedYear);
        int endIndex = 0;
        if (yearsIndexHash.containsValue(selectedYear+1)){
            endIndex = yearsIndexHash.get(selectedYear+1);
        }
        if (endIndex != 0){
            for (int i=startIndex; i<endIndex; i++){
                filteredList.add(movieList.get(i));
            }
        } else {
            for (int i=startIndex; i<movieList.size(); i++){
                if (movieList.get(i).getYear() == selectedYear){
                    filteredList.add(movieList.get(i));
                } else {
                    break;
                }
            }
        }
        if (filteredList != null) {
            moviesLiveData.postValue(filteredList);
        }

        return moviesLiveData;
    }

}
