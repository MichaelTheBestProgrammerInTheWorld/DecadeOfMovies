package com.michaelmagdy.decadeofmovies.model.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?method=flickr.photos.search&api_key=acea70bae33844579b14e99f26ed96ab&format=json&nojsoncallback=1&page=1&per_page=10&tags=cinema")
    Call<ApiResponse> getMoviePics (
            @Query("text") String movieTitle
    );
}
