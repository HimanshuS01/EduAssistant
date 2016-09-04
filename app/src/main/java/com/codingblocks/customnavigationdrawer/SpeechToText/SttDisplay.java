package com.codingblocks.customnavigationdrawer.SpeechToText;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.codingblocks.customnavigationdrawer.R;

public class SttDisplay extends AppCompatActivity {

    TextView speech_to_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String para=intent.getStringExtra("Paragraph");
        setContentView(R.layout.activity_stt_display);
        speech_to_text=(TextView)findViewById(R.id.speechTotext);
        speech_to_text.setText(para);
    }
}
