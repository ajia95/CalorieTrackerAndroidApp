package com.example.user.calorietracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class Add extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

    }

    public void clickSearch(View view) {
        Intent i = new Intent(this, Search.class);
        startActivity(i);
    }

    public void clickCustom(View view) {
        Intent i = new Intent(this, Custom.class);
        startActivity(i);

    }

}