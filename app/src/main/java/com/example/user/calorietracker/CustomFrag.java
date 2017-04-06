package com.example.user.calorietracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CustomFrag extends Fragment implements View.OnClickListener{


    private OnFragmentInteractionListener2 mListener;

    EditText nameinput, caloriesinput, quantityinput;
    Button update;

    public CustomFrag() {
        // Required empty public constructor
    }
    public static CustomFrag newInstance(){
        return new CustomFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        nameinput = (EditText) view.findViewById(R.id.nameinput);
        caloriesinput = (EditText) view.findViewById(R.id.caloriesinput);
        quantityinput = (EditText) view.findViewById(R.id.quantityinput);
        update = (Button) view.findViewById(R.id.update);
        update.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener2) {
            mListener = (OnFragmentInteractionListener2) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onClick(View v) {
        String name = nameinput.getText().toString();
        int calories = Integer.parseInt(caloriesinput.getText().toString());
        float quantity = Float.parseFloat(quantityinput.getText().toString());

        switch (v.getId()) {
            case R.id.update:
                mListener.updateData(name, calories, quantity);
                break;
        }
    }

    public interface OnFragmentInteractionListener2 {
        public void updateData(String name, int calories, float quantity);
    }
}
