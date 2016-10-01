package com.codingblocks.customnavigationdrawer;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codingblocks.customnavigationdrawer.DBMS.BatchTable;
import com.codingblocks.customnavigationdrawer.DBMS.MyDatabase;
import com.codingblocks.customnavigationdrawer.DBMS.StudentTable;
import com.google.gson.Gson;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentListRecyclerView extends AppCompatActivity {

    RecyclerView recyclerView;

    static int student_count = 0;
    StudentModel studentModel;
    ArrayList<StudentModel> student_list;
    List<String> data_list;
    List<String> user_id_list;
    RecyclerAdapter adapter;
    int batch_id;
    ProgressDialog progressDialog;
    public String UserNames;
    List<CourseDescription> details;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_recycler_view);

        user_id_list = new ArrayList<>();

        final Intent intent = getIntent();
        batch_id = intent.getIntExtra("Batch_ID", 0);
        Log.i("BatchID", batch_id + "");
        refresh();

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setBackgroundDrawable(R.drawable.fab)
                //.setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView ItemIconOne = new ImageView(this);
        ItemIconOne.setImageDrawable(getDrawable(R.drawable.add_student));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);

        ImageView ItemIconTwo = new ImageView(this);
        ItemIconTwo.setImageDrawable(getDrawable(R.drawable.analyse));
        SubActionButton button1 = itemBuilder.setContentView(ItemIconOne).setLayoutParams(new FrameLayout.LayoutParams(250, 250)).build();
        SubActionButton button2 = itemBuilder.setContentView(ItemIconTwo).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .attachTo(actionButton)
                .build();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentListRecyclerView.this);
                builder.setTitle("Add new Student and its UserId");

                LinearLayout layout = new LinearLayout(StudentListRecyclerView.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                // Set up the input
                final EditText user_name = new EditText(StudentListRecyclerView.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                user_name.setInputType(InputType.TYPE_CLASS_TEXT);
                user_name.setHint("User Name");
                layout.addView(user_name);

                final EditText user_id = new EditText(StudentListRecyclerView.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                user_id.setInputType(InputType.TYPE_CLASS_TEXT);
                user_id.setHint("User Id");
                layout.addView(user_id);

                builder.setView(layout);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentModel = new StudentModel(student_count, user_name.getText().toString(), user_id.getText().toString(), batch_id);
                        student_count++;
                        final SQLiteDatabase db = MyDatabase.getInstance(StudentListRecyclerView.this).getWritableDatabase();
                        StudentTable.save(db, studentModel);
                        db.close();
                        refresh();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserNames = "";
                ShowProgressDialog();
                for (int i = 0; i < user_id_list.size(); i++) {
                    UserNames = UserNames + user_id_list.get(i).toString() + ",";
                }
                Log.i("User",UserNames);
                Intent intent1=new Intent();
                intent1.setClass(StudentListRecyclerView.this,ExpandTextView.class);
                intent1.putExtra("UserNames",UserNames);
                startActivity(intent1);

//                CourseDescription courseDescription=new CourseDescription();
//                String name="hgdhdfbjdbfjdfb";
//                String desc="bvchvbhcvbhcbnc nhvbchb bcbvhbcn bbfhdbjsnchjsbdbcbsjdbcjdbfbhvhjbfdfbvhbdv";
//                courseDescription.obj.add(new CourseDetail(name,desc));
//                Intent intent=new Intent();
//                intent.setClass(StudentListRecyclerView.this,ExpandTextView.class);
//                intent.putExtra("Course_Description", courseDescription);
//                startActivity(intent);

//                Log.i("UserNames", UserNames);

//                Call<List<CourseDescription>> Course_Description = ApiClient.getInterface().getDetails("HimanshuS1995");
//                Course_Description.enqueue(new Callback<List<CourseDescription>>() {
//                    @Override
//                    public void onResponse(Call<List<CourseDescription>> call, Response<List<CourseDescription>> response) {
//                        if (response.isSuccessful()) {
//                            details = response.body();
//                            Log.i("Details", details.get(0).desc.toString());
//                        } else {
//                            Toast.makeText(StudentListRecyclerView.this, response.code() + response.message(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<CourseDescription>> call, Throwable t) {
//                        Toast.makeText(StudentListRecyclerView.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
//                    }
//                });


//                String str=new Gson().toJson(details);
//                Log.i("abc",str);
//                Intent intent1 = new Intent();
//                intent1.setClass(StudentListRecyclerView.this, ExpandTextView.class);
////                intent1.putExtra("Object", new Gson().toJson(details));
//                intent1.putExtra("Object", (Serializable) details);
//                startActivity(intent1);
                DismissProgressDialog();
            }
        });
    }

    private void ShowProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(StudentListRecyclerView.this);
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

    private void refresh() {
        data_list = new ArrayList<>();
        final SQLiteDatabase db = MyDatabase.getInstance(StudentListRecyclerView.this).getReadableDatabase();
        student_list = StudentTable.getByArg(db, batch_id);
        if (student_list == null) {

        } else {
            for (int i = 0; i < student_list.size(); i++) {
                data_list.add(student_list.get(i).getStudent_name());
                user_id_list.add(student_list.get(i).getUser_name());
            }
            adapter = new RecyclerAdapter(this, data_list);
            recyclerView = (RecyclerView) findViewById(R.id.student_list_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }
    }
}