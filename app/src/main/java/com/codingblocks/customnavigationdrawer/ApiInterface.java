package com.codingblocks.customnavigationdrawer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by HIman$hu on 9/4/2016.
 */
public interface ApiInterface {


    @GET("topics.php")
    Call<List<CourseDescription>> getDetails(@Query("user") String userNames);
}
