package com.example.greenday.network;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int resultCount;
    private Results[] results;
}
