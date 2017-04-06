package com.example.user.calorietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;



public class Track extends AppCompatActivity {

    public int current;
    public int total;
    public int left;



    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        updateText();
    }

    public void updateText()
    {
        db = new MyDBHandler(this);
        total = db.getTarget();
        current = db.getCurrent();
        left=total-current;

        TextView currentcal = (TextView) findViewById(R.id.currentcal);
        TextView leftcal = (TextView) findViewById(R.id.leftcal);
        currentcal.setText("You have consumed " + Integer.toString(current) + " calories so far!");
        leftcal.setText("You have " + Integer.toString(total) + "-" + Integer.toString(current) + "=" + Integer.toString(left) + " calories left!");

    }


    public void clickAdd(View view){
        Intent i = new Intent(this, AddFrags.class);
        startActivity(i);

    }

    public void clickNewDay(View view){

        db = new MyDBHandler(this);
        db.resetCurrent();
        updateText();

    }
}
