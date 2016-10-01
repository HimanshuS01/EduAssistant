package com.codingblocks.customnavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandTextView extends AppCompatActivity {

    //    ExpandableTvRecyclerAdapter adapter;
//    RecyclerView recyclerView;
    ListView course_details;
    List<String> dataList;
    List<CourseDescription> details;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text_view);

        Intent intent = getIntent();
        String user_names = intent.getStringExtra("UserNames");
        Log.i("UserNames", user_names);
        course_details = (ListView) findViewById(R.id.courseDetail);
        dataList = new ArrayList<>();
        Call<List<CourseDescription>> Course_Description = ApiClient.getInterface().getDetails(user_names);
        Course_Description.enqueue(new Callback<List<CourseDescription>>() {

            @Override
            public void onResponse(Call<List<CourseDescription>> call, Response<List<CourseDescription>> response) {
                if (response.isSuccessful()) {
                    dataList.add("Api 4");
                    dataList.add("CSS 3");
                    dataList.add("jQuery 2");
                    dataList.add("Server 2");
                    dataList.add("App 2");
                    dataList.add("Graphics 1");
                    dataList.add("AJAX 1");
                    dataList.add("Bootstrap 1");
                    dataList.add("iOS 1");

                    adapter = new ArrayAdapter<String>(ExpandTextView.this, android.R.layout.simple_list_item_1, dataList);
                    course_details.setAdapter(adapter);//After this step you should be able to see the data in your list view.

                    List<CourseDescription> details = response.body();
                    for (int i = 0; i < details.size(); i++) {
                        dataList.add(details.get(i).getName());

                    }
                    Log.i("Details", details.get(0).desc.toString());
                } else {
                    Toast.makeText(ExpandTextView.this, response.code() + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseDescription>> call, Throwable t) {
                Toast.makeText(ExpandTextView.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }
        });


//        adapter = new ArrayAdapter<String>(ExpandTextView.this, android.R.layout.simple_list_item_1, dataList);
//        course_details.setAdapter(adapter);//After this step you should be able to see the data in your list view.

////        String str=intent.getStringExtra("Object");
////        Log.i("abc",str);
////        Gson gson=new Gson();
////        JSONObject obj = null;
////        try {
////            obj=new JSONObject(str);
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
////
////        ArrayList<String> arr=new ArrayList<>();
////        Iterator iter=obj.keys();
////        while(iter.hasNext()){
////            String key= (String) iter.next();
////            try {
////                arr.add(obj.getString(key));
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////        }
//        List<CourseDescription> courseDescription= (List<CourseDescription>) gson.fromJson(str,StudentListRecyclerView.class);
//      List<CourseDescription> courseDescription= (List<CourseDescription>) intent.getSerializableExtra("Object");
//        Log.i("ExpandTextView",courseDescription.get(0).getDesc().toString());
//        adapter = new ExpandableTvRecyclerAdapter(this, arr);
//        recyclerView = (RecyclerView) findViewById(R.id.Expandable_TV_RecyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }
}
