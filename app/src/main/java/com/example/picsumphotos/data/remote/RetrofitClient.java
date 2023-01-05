package com.example.picsumphotos.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final Retrofit retrofitInstance = new Retrofit.Builder()
            .baseUrl(ConstantsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //empty private constructor
    private RetrofitClient() {
    }

    public static Retrofit getRetrofitInstance(){
        return retrofitInstance;
    }
}
