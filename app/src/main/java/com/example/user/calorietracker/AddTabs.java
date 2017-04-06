package com.example.user.calorietracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class AddTabs extends AppCompatActivity implements SearchFrag.OnFragmentInteractionListener1, CustomFrag.OnFragmentInteractionListener2 {

    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tabs);

       /* if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frags, new SearchFrag())
                    .commit();
        }*/
    }




   public void onClick1(View view)
    {
       /* SearchFrag frag1 = new SearchFrag();

        getFragmentManager().beginTransaction()
                .replace(R.id.frags, frag1)
                .addToBackStack(null)
                .commit(); */
    }

    public void onClick2(View view)
    {
       /* CustomFrag frag2 = new CustomFrag();

        getFragmentManager().beginTransaction()
                .replace(R.id.frags, frag2)
                .addToBackStack(null)
                .commit();*/
    }



    @Override
    public void updateData(String name, int calories, float quantity)
    {
        db = new MyDBHandler(this);
        int newCal = Math.round(quantity*calories);
        Items item = new Items(name, calories);
        db.addItem(item);
        db.updateCurrent(newCal);
        db.close();
        Intent i = new Intent(this, Track.class);
        startActivity(i);
    }


    @Override
    public List<Items> populateList() {
        MyDBHandler db = new MyDBHandler(this);
        List<Items> itemList = db.getItems();
        db.close();
        return itemList;
    }

    @Override
    public void showInputDialog(final int itemCals) {
        // get prompts.xml view
        Log.e("fuck", "1");
        LayoutInflater layoutInflater = LayoutInflater.from(AddTabs.this);
        Log.e("fuck", "1");
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        Log.e("fuck", "1");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddTabs.this);
        Log.e("fuck", "1");
        alertDialogBuilder.setView(promptView);
        Log.e("fuck","1");
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        Log.e("fuck", "1");
        // setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {

            public void onClick(DialogInterface dialog, int id) {

                int quant = Integer.parseInt(editText.getText().toString());
                useInput(quant*itemCals);
                        /*MyDBHandler db = new MyDBHandler(this);
                        db.updateCurrent(inputCal);
                        db.close();
                        Intent i = new Intent(this, Track.class);
                        startActivity(i);*/
            }
        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    public void useInput(int cals)
    {
        MyDBHandler db = new MyDBHandler(this);
        db.updateCurrent(cals);
        db.close();
        Intent i = new Intent(this, Track.class);
        startActivity(i);
    }


}
