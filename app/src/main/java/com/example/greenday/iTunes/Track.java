package com.example.greenday.iTunes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Track {
    private int trackId;
    private String artworkUrl100, trackName, collectionName, artistName;
    private boolean favorite;
}