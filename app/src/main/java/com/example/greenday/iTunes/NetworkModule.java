//package com.example.greenday.iTunes;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import dagger.hilt.InstallIn;
//import dagger.hilt.android.components.ActivityRetainedComponent;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//@Module
//@InstallIn(ActivityRetainedComponent.class)
//public class NetworkModule {
//
//    private static final String URL = "https://itunes.apple.com/";
//    private static API api;
//
//    @Provides
//    @Singleton
//    public static API getApi() {
//        if (api == null) {
//            api = new Retrofit.Builder()
//                    .baseUrl(URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build().create(API.class);
//        }
//        return api;
//    }
//}
