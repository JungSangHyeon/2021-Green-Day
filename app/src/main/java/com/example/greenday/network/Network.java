package com.example.greenday.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static final String URL = "https://itunes.apple.com/";

    private static Retrofit instance;
//    private Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getInstance(){
        if(instance==null){
            instance = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
