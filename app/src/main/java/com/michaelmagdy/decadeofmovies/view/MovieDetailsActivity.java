package com.michaelmagdy.decadeofmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.Movie;
import com.michaelmagdy.decadeofmovies.model.webservice.Photo;
import com.michaelmagdy.decadeofmovies.viewmodel.MovieDetailsActivityViewModel;

import java.util.List;

import static com.michaelmagdy.decadeofmovies.view.MainActivity.INTENT_TAG;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTxt, yearTxt, castTxt, genreTxt, noPhotosTxt;
    private RecyclerView picsRecycler;
    private PicsListAdapter picsListAdapter;
    private MovieDetailsActivityViewModel detailsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(INTENT_TAG);
        initUI(movie);
        detailsActivityViewModel = new ViewModelProvider(this)
                .get(MovieDetailsActivityViewModel.class);
        final String searchStr = movie.getTitle();
        detailsActivityViewModel.getPhotoLiveData(searchStr).observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                if (photos.isEmpty()){
                    noPhotosTxt.setVisibility(View.VISIBLE);
                } else {
                    initListAdapter(photos);
                }

            }
        });
    }

    private void initUI(Movie movie) {

        titleTxt = findViewById(R.id.md_title);
        yearTxt = findViewById(R.id.md_year);
        genreTxt = findViewById(R.id.md_genre);
        castTxt = findViewById(R.id.md_cast);
        noPhotosTxt = findViewById(R.id.md_no_photos);

        titleTxt.setText(movie.getTitle());
        yearTxt.setText("Year : " + String.valueOf(movie.getYear()));
        genreTxt.setText("Genre : " + movie.getGenres().toString().replaceAll("\\[|\\]", ""));
        castTxt.setText("Cast : " + movie.getCast().toString().replaceAll("\\[|\\]", ""));

        picsRecycler = findViewById(R.id.md_pics_list);
        picsRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        picsRecycler.setHasFixedSize(true);
    }

    private void initListAdapter(List<Photo> mList){

        picsListAdapter = new PicsListAdapter(mList);
        picsRecycler.setAdapter(picsListAdapter);
    }
}
