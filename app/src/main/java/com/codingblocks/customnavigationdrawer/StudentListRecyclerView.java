package com.codingblocks.customnavigationdrawer;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class StudentListRecyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    String Username,UserId;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_recycler_view);
        Intent intent=getIntent();
        int batch_id=intent.getIntExtra("Batch_ID",0);


        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setBackgroundDrawable(R.drawable.fab)
                        //.setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView ItemIconOne = new ImageView(this);
        ItemIconOne.setImageDrawable(getDrawable(R.drawable.add_student));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50,50);

        ImageView ItemIconTwo = new ImageView(this);
        ItemIconTwo.setImageDrawable(getDrawable(R.drawable.analyse));
        SubActionButton button1 = itemBuilder.setContentView(ItemIconOne).setLayoutParams(new FrameLayout.LayoutParams(250,250)).build();
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
                builder.setTitle("Add new  Student and its id");

                LinearLayout layout=new LinearLayout(StudentListRecyclerView.this);
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

        RecyclerAdapter adapter = new RecyclerAdapter(this);
        recyclerView=(RecyclerView)findViewById(R.id.student_list_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
