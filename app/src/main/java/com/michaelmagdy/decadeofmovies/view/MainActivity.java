package com.michaelmagdy.decadeofmovies.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.Movie;
import com.michaelmagdy.decadeofmovies.view.MoviesListAdapter;
import com.michaelmagdy.decadeofmovies.viewmodel.MainActivityViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    //private List<Movie> movieList;
    private MoviesListAdapter moviesListAdapter;
    private RecyclerView moviesRecyclerView;
    private InputStream inputStream;
    private FloatingActionButton fabSearch;

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
        handleClicks();
    }

    private void initUi() {

        moviesRecyclerView = findViewById(R.id.recycler_view);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesRecyclerView.setHasFixedSize(true);

        fabSearch = findViewById(R.id.floatingActionButton);
    }

    private void initListAdapter(List<Movie> mList){

        moviesListAdapter = new MoviesListAdapter(mList);
        moviesRecyclerView.setAdapter(moviesListAdapter);
    }

    private void handleClicks(){

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSearchUI();
            }
        });
    }

    private void createSearchUI() {
        
        List<Integer> yearsList = mainActivityViewModel.getYearsList();
        List<String> newList = yearsList.stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.toList());
        String[] yearsArray = newList.toArray(new String[0]);
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearsArray);

        final Spinner spYears = new Spinner(this);
        spYears.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spYears.setAdapter(adp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.filter));
        builder.setMessage(getString(R.string.filter_msg));
        builder.setPositiveButton(getString(R.string.searh), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedYearStr = spYears.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, selectedYearStr, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(spYears);
        builder.create().show();
    }
}
