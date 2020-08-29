package com.michaelmagdy.decadeofmovies.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.michaelmagdy.decadeofmovies.model.Movie;
import com.michaelmagdy.decadeofmovies.model.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private Repository repository;
    List<Integer> yearsList;

    public MainActivityViewModel() {

        repository = new Repository();
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData(InputStream inputStream) {
        repository.setInputStream(inputStream);
        moviesLiveData = repository.getMoviesLiveData();
        return moviesLiveData;
    }

    public List<Integer> getYearsList() {
        yearsList = repository.getYearsList();
        return yearsList;
    }
}
