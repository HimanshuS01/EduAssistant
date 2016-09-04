package com.codingblocks.customnavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ExpandTextView extends AppCompatActivity {

    ExpandableTvRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text_view);

        Intent intent=getIntent();
        String str=intent.getStringExtra("Object");
        Gson gson=new Gson();
        List<CourseDescription> courseDescription= (List<CourseDescription>) gson.fromJson(str,StudentListRecyclerView.class);
//      List<CourseDescription> courseDescription= (List<CourseDescription>) intent.getSerializableExtra("Object");
        Log.i("ExpandTextView",courseDescription.get(0).getDesc().toString());
        adapter = new ExpandableTvRecyclerAdapter(this, courseDescription);
        recyclerView = (RecyclerView) findViewById(R.id.Expandable_TV_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
