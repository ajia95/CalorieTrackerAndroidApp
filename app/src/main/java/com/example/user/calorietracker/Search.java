package com.example.user.calorietracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListViewAdapter adapter;
    SearchView search;
    ListView listView;
    List<Items> itemList = new ArrayList<Items>();
    int itemCals;
   // private String m_Text = "";
    //private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //context = getContext();

        MyDBHandler db = new MyDBHandler(this);
        itemList = db.getItems();

        adapter = new ListViewAdapter(this, itemList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        db.close();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                view.setSelected(true);
                //listView.setSelector(position);
               Items selectedItem = adapter.getItem(position); //
               itemCals = selectedItem.getCalories(); //getter method
                showInputDialog();
             }
        });

        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(this);

    }

    public void useInput(int cals)
    {
        MyDBHandler db = new MyDBHandler(this);
        db.updateCurrent(cals);
        db.close();
        Intent i = new Intent(this, Track.class);
        startActivity(i);
    }

    protected void showInputDialog() {

        // get prompts.xml view
        Log.e("fuck", "1");
        LayoutInflater layoutInflater = LayoutInflater.from(Search.this);
        Log.e("fuck", "1");
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        Log.e("fuck", "1");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Search.this);
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

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String text = newText;
        adapter.filter(text);
        return false;
    }

}