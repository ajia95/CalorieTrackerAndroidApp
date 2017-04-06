package com.example.user.calorietracker;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.widget.SearchView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class SearchFrag extends Fragment implements ListView.OnItemClickListener, SearchView.OnQueryTextListener{

    ListViewAdapter adapter;
    android.widget.SearchView search;
    ListView listView;
    List<Items> itemList = new ArrayList<Items>();

    private OnFragmentInteractionListener1 mListener;

    public SearchFrag() {
        // Required empty public constructor
    }
    public static SearchFrag newInstance(){
        return new SearchFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        itemList = mListener.populateList();

        adapter = new ListViewAdapter(getActivity(), itemList);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(this);

        search = (android.widget.SearchView) view.findViewById(R.id.search);
        search.setOnQueryTextListener(this);


        return view;
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener1) {
            mListener = (OnFragmentInteractionListener1) context;
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

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // mListener.handleClick(view);
            view.setSelected(true);
            Items selectedItem = adapter.getItem(i); //
            int itemCals = selectedItem.getCalories(); //getter method
            mListener.showInputDialog(itemCals);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filter(s);
        return false;
    }


    public interface OnFragmentInteractionListener1 {
        public List<Items> populateList();
        public void showInputDialog(int itemCals);
    }
}
