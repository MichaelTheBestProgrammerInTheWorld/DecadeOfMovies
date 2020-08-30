package com.michaelmagdy.decadeofmovies.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaelmagdy.decadeofmovies.R;
import com.michaelmagdy.decadeofmovies.model.webservice.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PicsListAdapter extends RecyclerView.Adapter<PicsListAdapter.PicsListViewHolder> {

    private List<Photo> photoList;

    public PicsListAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PicsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pics_list_item, parent, false);
        return new PicsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicsListViewHolder holder, int position) {

        if (photoList.isEmpty()){

        } else {
            Photo photo = photoList.get(position);
            String farm, server, id, secret;
            farm = String.valueOf(photo.getFarm());
            server = photo.getServer();
            id = photo.getId();
            secret = photo.getSecret();
            final String picUrl =
                    "http://farm" + farm + ".static.flickr.com/" + server +
                            "/" + id + "_" + secret + ".jpg";
            Picasso.get().load(picUrl).into(holder.moviePicImgV);
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class PicsListViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePicImgV;

        public PicsListViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePicImgV = itemView.findViewById(R.id.pli_image);
        }
    }
}
