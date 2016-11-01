package com.codingblocks.ChatBot_And_InterestRanker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codingblocks.customnavigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsInterestList extends AppCompatActivity {

    //ExpandableTvRecyclerAdapter adapter;
    //RecyclerView recyclerView;

    ListView course_details;
    List<String> dataList;
    ArrayAdapter<String> adapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_expand_text_view);
        ShowProgressDialog();
        setTitle("Students Interest List");
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
                    List<CourseDescription> details = response.body();
                    for (int i = 0; i < details.size(); i++) {
                        dataList.add(details.get(i).getName());
                        Log.i("Details", details.get(i).getName());
                    }

                    //Log.i("Details", details.get(i).toString());
                }


                else {
                    Toast.makeText(StudentsInterestList.this, response.code() + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseDescription>> call, Throwable t) {
                Toast.makeText(StudentsInterestList.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            }
        });

        DismissProgressDialog();


        adapter = new ArrayAdapter<String>(StudentsInterestList.this, android.R.layout.simple_list_item_1, dataList);
        course_details.setAdapter(adapter);//After this step you should be able to see the data in your list view.

//        List<CourseDescription> courseDescription= (List<CourseDescription>) gson.fromJson(str,StudentNamesList.class);
//        List<CourseDescription> courseDescription= (List<CourseDescription>) intent.getSerializableExtra("Object");
//        Log.i("StudentsInterestList",courseDescription.get(0).getDesc().toString());
//        adapter = new ExpandableTvRecyclerAdapter(this, arr);
//        recyclerView = (RecyclerView) findViewById(R.id.Expandable_TV_RecyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }

    private void ShowProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(StudentsInterestList.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Analysing common interest of student..");
        progressDialog.show();
    }

    private void DismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}

