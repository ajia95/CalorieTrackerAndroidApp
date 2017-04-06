package com.example.user.calorietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class home extends AppCompatActivity {

    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


       // db = new MyDBHandler(this);



    }

    public void enter(View view)
    {
        EditText target = (EditText) findViewById(R.id.target);
        TextView warning = (TextView) findViewById(R.id.warning);

        String check = target.getText().toString();

        db = new MyDBHandler(this);

        if(check.equals("")) {
            // Intent a = new Intent(this, MainActivity.class);
            //startActivity(a);
            warning.setText("Please enter a value");
            return;
        }
        else {
            int input = Integer.parseInt(check);
            Log.d("tag", "111\n");
            db.setTarget(input);
            Log.d("tag", "222\n");
            //int i = db.getTarget();

            //Log.d("tag","input is " + i );


            Intent a = new Intent(this, MainActivity.class);
            startActivity(a);



        }




    }


}
