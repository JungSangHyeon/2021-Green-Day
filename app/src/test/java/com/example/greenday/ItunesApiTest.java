package com.example.greenday;

import com.example.greenday.remoteDataSource.ItunesApi;
import com.example.greenday.remoteDataSource.ItunesApiCreator;
import com.example.greenday.remoteDataSource.Track;

import org.junit.Test;

import java.util.ArrayList;

public class ItunesApiTest {

    @Test
    public void isEveryCallReturnSameResult() {
        int limit = 100, sleepTime = 5000;

        ItunesApi api = ItunesApiCreator.create();
        api.search("greenday", "song", 0, limit).subscribe(result1 -> {
            try { Thread.sleep(sleepTime); } catch (InterruptedException e) { e.printStackTrace(); }
            api.search("greenday", "song", 0, limit).subscribe(result2 -> {
                for (int i = 0; i < limit; i++) {
                    int id1 = result1.getResults().get(i).getTrackId();
                    int id2 = result2.getResults().get(i).getTrackId();
                    if(id1!=id2) System.out.println(id1 + " != " + id2);
                }
            });
        });
    }

    @Test
    public void isResultOverlap() {
        int limit = 20, sleepTime = 5000;

        ItunesApi api = ItunesApiCreator.create();
        api.search("greenday", "song", 0, limit).subscribe(result1 -> {
            try { Thread.sleep(sleepTime); } catch (InterruptedException e) { e.printStackTrace(); }
            api.search("greenday", "song", limit, limit).subscribe(result2 -> {
                ArrayList<Integer> result1Ids = new ArrayList<>();
                for(Track track : result1.getResults()) result1Ids.add(track.getTrackId());
                for(Track track : result2.getResults()) {
                    int trackId = track.getTrackId();
                    if(result1Ids.contains(trackId)) System.out.println("Overlap " + trackId);
                }
            });
        });
    }
}