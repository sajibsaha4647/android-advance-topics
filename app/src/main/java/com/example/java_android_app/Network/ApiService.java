package com.example.java_android_app.Network;

import com.example.java_android_app.model.PostModel;

import retrofit2.Call;
import retrofit2.http.GET;

// Retrofit needs an INTERFACE, not a class
public interface ApiService {

    @GET("posts/1")
    Call<PostModel> getPost();
}