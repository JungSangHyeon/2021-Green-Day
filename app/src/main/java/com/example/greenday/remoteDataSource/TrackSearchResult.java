package com.example.greenday.remoteDataSource;

import java.util.ArrayList;

public class TrackSearchResult {
    private int resultCount;
    private ArrayList<Track> results;

    public int getResultCount() { return resultCount; }
    public void setResultCount(int resultCount) { this.resultCount = resultCount; }
    public ArrayList<Track> getResults() { return results; }
    public void setResults(ArrayList<Track> results) { this.results = results; }
}
