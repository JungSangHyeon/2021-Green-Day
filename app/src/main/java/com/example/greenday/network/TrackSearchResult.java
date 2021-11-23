package com.example.greenday.network;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackSearchResult {
    private int resultCount;
    private ArrayList<Track> results;
}
