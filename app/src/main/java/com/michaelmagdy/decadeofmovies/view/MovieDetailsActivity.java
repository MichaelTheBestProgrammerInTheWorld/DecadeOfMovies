package com.michaelmagdy.decadeofmovies.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.Movie;

import static com.michaelmagdy.decadeofmovies.view.MainActivity.INTENT_TAG;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTxt, yearTxt, castTxt, genreTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(INTENT_TAG);
        initUI(movie);

    }

    private void initUI(Movie movie) {

        titleTxt = findViewById(R.id.md_title);
        yearTxt = findViewById(R.id.md_year);
        genreTxt = findViewById(R.id.md_genre);
        castTxt = findViewById(R.id.md_cast);

        titleTxt.setText(movie.getTitle());
        yearTxt.setText("Year : " + String.valueOf(movie.getYear()));
//        for (String genre : movie.getGenres()){
//            genreTxt.setText("Genre : " + genre.concat(genre));
//        }
        genreTxt.setText("Genre : " + movie.getGenres().toString().replaceAll("\\[|\\]", ""));
        castTxt.setText("Cast : " + movie.getCast().toString().replaceAll("\\[|\\]", ""));

    }
}
