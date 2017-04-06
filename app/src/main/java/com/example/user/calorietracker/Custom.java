package com.example.user.calorietracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.effect.Effect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Custom extends AppCompatActivity {

    MyDBHandler db;
    EditText nameinput;
    EditText caloriesinput;
    EditText quantityinput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        nameinput = (EditText) findViewById(R.id.nameinput);
        caloriesinput = (EditText) findViewById(R.id.caloriesinput);
        quantityinput = (EditText) findViewById(R.id.quantityinput);

    }


    public void clickUpdate(View view){

        db = new MyDBHandler(this);
        String name = nameinput.getText().toString();
        int calories = Integer.parseInt(caloriesinput.getText().toString());
        float quantity = Float.parseFloat(quantityinput.getText().toString());
        int newCal = Math.round(quantity*calories);
        Items item = new Items(name, calories);
        db.addItem(item);
        db.updateCurrent(newCal);
        db.close();
        Intent i = new Intent(this, Track.class);
        startActivity(i);

    }
}
