package com.example.greenday.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/search?")
    Call<TrackSearchResult> search(
            @Query("term") String term,
            @Query("entity") String entity,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
}
