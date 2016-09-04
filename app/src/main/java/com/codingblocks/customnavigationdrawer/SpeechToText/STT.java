package com.codingblocks.customnavigationdrawer.SpeechToText;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codingblocks.customnavigationdrawer.R;

import java.util.ArrayList;
import java.util.Locale;

public class STT extends AppCompatActivity {

    private View full_view;
    int SPEECH_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stt);
        full_view = (View) findViewById(R.id.FullView);
        full_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechToText();
            }
        });
    }


    private void speechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something");

        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);

        } catch (ActivityNotFoundException a) {

            //display error message
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SPEECH_REQUEST_CODE) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text = result.get(0);
                Intent intent=new Intent(STT.this,SttDisplay.class);
                intent.putExtra("Paragraph",text);
                startActivity(intent);
//                TextView speechOutput = (TextView) findViewById(R.id.speech_output);
//                speechOutput.setText(text);
            }
        }
    }
}
