package com.example.newproject.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoHolderApi {
    @GET("/api/activity")
    Call<ModelDo> getActivities();

    @GET("/api/activity/{key}")
    Call<ModelDo> getActivityByKey(@Query("key") String key);

    @GET("/api/activity?price")
    Call<ModelDo> getActivityByPrice(@Query("price") double price);
}
