package com.apps.nybizz.Utils;

import android.content.Context;

import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Network.WebApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {

    private static WebApi requestAPI;
    public static Retrofit getClient() {

        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://nybizz.clientdemo.cf")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }



    public static WebApi getClientNew(Context context) {
        Retrofit retrofit = null;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //final PreManager preManager=new PreManager(context);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder().addHeader("APP_KEY", "8447126401").build();
                Request request = chain.request().newBuilder().addHeader("Authorization","Bearer " + SharedPrefsUtils.getSharedPreferenceString(context,"access_token")).build();
                return chain.proceed(request);
            }
        }).addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://nybizz.clientdemo.cf")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        requestAPI = retrofit.create(WebApi.class);
        return requestAPI;
    }


}
