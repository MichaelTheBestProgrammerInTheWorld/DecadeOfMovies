package com.michaelmagdy.decadeofmovies.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.Movie;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {

    private List<Movie> movieList;

    public MoviesListAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list_item, parent, false);
        return new MoviesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListViewHolder holder, int position) {

        holder.titleTxt.setText(movieList.get(position).getTitle());
        holder.yearTxt.setText(String.valueOf(movieList.get(position).getYear()));
        holder.ratingBar.setRating((float) movieList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MoviesListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt, yearTxt;
        RatingBar ratingBar;

        public MoviesListViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.li_title);
            yearTxt = itemView.findViewById(R.id.li_year);
            ratingBar = itemView.findViewById(R.id.li_ratingBar);
            ratingBar.setEnabled(false);
        }
    }
}
