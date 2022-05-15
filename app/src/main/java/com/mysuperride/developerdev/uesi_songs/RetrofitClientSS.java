package com.mysuperride.developerdev.uesi_songs;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientSS {

    public static final String BASE_URL  = "http://sscripture.appletjar.com/public/api/";

    public static RetrofitClientSS mInstance;
    private Retrofit retrofit;

    private RetrofitClientSS(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClientSS getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClientSS();

        }
        return mInstance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);

    }

}
