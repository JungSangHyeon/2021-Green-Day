package com.example.greenday.di;

import android.content.Context;

import com.example.greenday.database.FavoriteDao;
import com.example.greenday.database.FavoriteDatabase;
import com.example.greenday.iTunes.ItunesApi;
import com.example.greenday.iTunes.ItunesApiCreator;
import com.example.greenday.repository.Repository;
import com.example.greenday.repository.RepositoryImpl;

import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@dagger.Module
@InstallIn(ViewModelComponent.class)
public class DataSourceModule {

    @Provides
    public static Repository provideRepository(@ApplicationContext Context context){
        return new RepositoryImpl(provideFavoriteDao(context), provideAPI());
    }

    public static FavoriteDao provideFavoriteDao(@ApplicationContext Context context){
        return FavoriteDatabase.getFavoriteDao(context);
    }

    public static ItunesApi provideAPI(){
        return ItunesApiCreator.create();
    }
}
