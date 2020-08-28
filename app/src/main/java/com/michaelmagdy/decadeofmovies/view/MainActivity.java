package com.michaelmagdy.decadeofmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.Movie;
import com.michaelmagdy.decadeofmovies.view.MoviesListAdapter;
import com.michaelmagdy.decadeofmovies.viewmodel.MainActivityViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    //private List<Movie> movieList;
    private MoviesListAdapter moviesListAdapter;
    private RecyclerView moviesRecyclerView;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        try {
            inputStream = this.getAssets().open("movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMoviesLiveData(inputStream).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                initListAdapter(movies);
            }
        });
    }

    private void initUi() {

        moviesRecyclerView = findViewById(R.id.recycler_view);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesRecyclerView.setHasFixedSize(true);
    }

    private void initListAdapter(List<Movie> mList){

        moviesListAdapter = new MoviesListAdapter(mList);
        moviesRecyclerView.setAdapter(moviesListAdapter);
    }
}
