/**
 * Created By Achmad Yusuf
 * Github : abdullah1006
 **/
package com.example.crudmysql.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    //Isi dengan IP kalian masing masing
    private static final String base_url = "http://192.168.42.66/crud_ci4/public/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
    public static APIRequestUser getUserAPI(){
        APIRequestUser userAPI = getRetrofit().create(APIRequestUser.class);
        return  userAPI;
    }
}
