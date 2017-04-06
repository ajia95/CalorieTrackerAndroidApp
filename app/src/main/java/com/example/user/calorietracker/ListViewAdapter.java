package com.example.user.calorietracker;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<Items> itemList = null;
    private ArrayList<Items> arraylist;

//changed Context to SearchFrag for frag test
    public ListViewAdapter(Context context, List<Items> itemList) {
        mContext = context;
        this.itemList = itemList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Items>();
        this.arraylist.addAll(itemList);
    }

    public class ViewHolder {
        TextView name;
        TextView calories;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Items getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {


        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.name = (TextView) view.findViewById(R.id.nameLabel);
            holder.calories = (TextView) view.findViewById(R.id.caloriesLabel);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(itemList.get(position).getName() + ":\t");
        holder.calories.setText(itemList.get(position).getCalories() + " calories");



        return view;

    }




    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        itemList.clear();
        if (charText.length() == 0) {
            itemList.addAll(arraylist);
        } else {
            for (Items wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    itemList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}