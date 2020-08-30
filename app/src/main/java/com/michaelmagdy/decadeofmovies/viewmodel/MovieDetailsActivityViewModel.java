package com.michaelmagdy.decadeofmovies.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.michaelmagdy.decadeofmovies.model.Repository;
import com.michaelmagdy.decadeofmovies.model.webservice.Photo;

import java.util.List;

public class MovieDetailsActivityViewModel extends ViewModel {

    private MutableLiveData<List<Photo>> photoLiveData = new MutableLiveData<>();
    private Repository repository;

    public MovieDetailsActivityViewModel() {

        repository = new Repository();
    }

    public MutableLiveData<List<Photo>> getPhotoLiveData(String query) {
        photoLiveData = repository.loadPics(query);
        return photoLiveData;
    }
}
