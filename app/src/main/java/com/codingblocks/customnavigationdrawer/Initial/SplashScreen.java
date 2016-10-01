package com.codingblocks.customnavigationdrawer.Initial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codingblocks.customnavigationdrawer.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }
}
