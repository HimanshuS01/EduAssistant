package com.codingblocks.customnavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ExpandTextView extends AppCompatActivity {

    ExpandableTvRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text_view);

        Intent intent=getIntent();
        CourseDescription courseDescription= (CourseDescription) intent.getSerializableExtra("Course_Description");
        adapter = new ExpandableTvRecyclerAdapter(this, courseDescription);
        recyclerView = (RecyclerView) findViewById(R.id.Expandable_TV_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
