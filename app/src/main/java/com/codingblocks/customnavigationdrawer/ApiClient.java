package com.codingblocks.customnavigationdrawer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HIman$hu on 9/4/2016.
 */
public class ApiClient {
    private static ApiInterface mService;

    public static ApiInterface getInterface() {
        if (mService == null) {
            Gson gson = new GsonBuilder().create();
            //.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
            //.create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://code-and-counter.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            mService = retrofit.create(ApiInterface.class);
        }
        return mService;
    }

}
