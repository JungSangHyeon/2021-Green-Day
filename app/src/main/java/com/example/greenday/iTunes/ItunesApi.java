package com.example.greenday.iTunes;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesApi {

    @GET("/search?") // Ex) https://itunes.apple.com/search?term=greenday&entity=song&offset=0&limit=20
    Single<TrackSearchResult> search (
            @Query("term") String term,
            @Query("entity") String entity,
            @Query("offset") int offset,
            @Query("limit") int limit
    );

    @GET("/lookup?") // Ex) https://itunes.apple.com/lookup?id=1068455608,317526898
    Single<TrackSearchResult> searchWithTrackId (
            @Query("id") String ids
    );
}


