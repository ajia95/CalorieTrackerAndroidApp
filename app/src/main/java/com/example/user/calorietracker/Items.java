package com.example.user.calorietracker;


import android.util.Log;

public class Items {


    private String name;
    private int calories;

   public Items(String name, int calories){
       this.name = name;
       this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}

