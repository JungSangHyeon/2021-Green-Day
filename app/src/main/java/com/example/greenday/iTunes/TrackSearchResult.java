package com.example.greenday.iTunes;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackSearchResult {
    private int resultCount;
    private ArrayList<Track> results;
}
