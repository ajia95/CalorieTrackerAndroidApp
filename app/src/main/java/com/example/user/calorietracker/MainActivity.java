package com.example.user.calorietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTrack(View view){
        Log.d("tag", "traaaack\n");
        Intent i = new Intent(this, Track.class);
        startActivity(i);
    }
    public void onReset(View view){
        Intent i = new Intent(this, home.class);
        startActivity(i);
    }
}
