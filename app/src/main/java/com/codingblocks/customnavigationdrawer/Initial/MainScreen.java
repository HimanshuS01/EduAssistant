package com.codingblocks.customnavigationdrawer.Initial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codingblocks.customnavigationdrawer.ChatbotFiles.ChatActivity;
import com.codingblocks.customnavigationdrawer.MainActivity;
import com.codingblocks.customnavigationdrawer.R;
import com.codingblocks.customnavigationdrawer.SpeechToText.STT;
import com.q42.android.scrollingimageview.ScrollingImageView;

public class MainScreen extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Button b1=(Button)findViewById(R.id.button3);
        Button b2=(Button) findViewById(R.id.button2);
        Button b3=(Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainScreen.this,STT.class);
                startActivity(intent);
            }        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainScreen.this, ChatActivity.class);
                startActivity(intent);

            }
        });


    }
}
