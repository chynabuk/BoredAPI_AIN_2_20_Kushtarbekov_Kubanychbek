package com.example.newproject.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public RetrofitBuilder() {
    }

    private static DoHolderApi instance;

    public static DoHolderApi getInstance() {
        if (instance == null){
            instance = init();
        }
        return instance;
    }

    public static DoHolderApi init() {
        return new Retrofit
                .Builder()
                .baseUrl("https://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DoHolderApi.class);
    }
}
