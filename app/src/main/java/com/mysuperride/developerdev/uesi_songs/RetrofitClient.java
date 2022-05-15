package com.mysuperride.developerdev.uesi_songs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL  = "http://vg.abiscube.com/public/api/";

    public static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();

        }
        return mInstance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);

    }

}
