package com.codingblocks.ChatBot_And_InterestRanker.Initial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codingblocks.ChatBot_And_InterestRanker.StudentBatches;
import com.codingblocks.ChatBot_And_InterestRanker.ChatbotFiles.ChatActivity;
import com.codingblocks.customnavigationdrawer.R;

public class MainScreen extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, StudentBatches.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainScreen.this, ChatActivity.class);
                startActivity(intent);

            }
        });
    }
}
