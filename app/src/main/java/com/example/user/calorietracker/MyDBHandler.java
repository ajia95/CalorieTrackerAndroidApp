package com.example.user.calorietracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MyDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "items.db";
    //table items
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CALORIES = "calories";
    //table track values
    public static final String TABLE_STATS = "stats";
    public static final String COLUMN_TARGET = "target";
    public static final String COLUMN_CURRENT = "current";

    public MyDBHandler(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_ITEMS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CALORIES + " INT);";

        String query2 = "CREATE TABLE " + TABLE_STATS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TARGET + " INT, " +
                COLUMN_CURRENT + " INT);";

        db.execSQL(query1);
        db.execSQL(query2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        onCreate(db);
    }




    public void addItem(Items item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
       values1.put(COLUMN_NAME, item.getName());
       values1.put(COLUMN_CALORIES, item.getCalories());


        db.insert(TABLE_ITEMS, null, values1);
        db.close();
    }

    public List<Items> getItems()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Items> itemList = new ArrayList<Items>();

        Cursor  c = db.rawQuery("SELECT * FROM " + TABLE_ITEMS,null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                String name = c.getString(c.getColumnIndex(COLUMN_NAME));
                int calories = Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_CALORIES)));
                Items item = new Items(name, calories);
                itemList.add(item);
                c.moveToNext();
            }
        }


        c.close();
        db.close();
        return itemList;
    }






    public void setTarget(int tar){

        SQLiteDatabase db1 = this.getReadableDatabase();
        String[] columns = {COLUMN_TARGET};
        Cursor c = db1.query(TABLE_STATS, columns, null, null, null, null, null, String.valueOf(1));
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_TARGET, tar);
        if(c.getCount()==0)
        {
            values1.put(COLUMN_CURRENT, 0);
            db2.insert(TABLE_STATS, null, values1);
        }
        else
        {
            db2.update(TABLE_STATS, values1, COLUMN_ID + "=" + 1, null);
        }

        c.close();
        db1.close();
        db2.close();
    }


    public int getTarget() {
       SQLiteDatabase db = this.getReadableDatabase();
       int targetVal=1000000;

        String[] columns = {COLUMN_TARGET};
        Cursor c = db.query(TABLE_STATS, columns, null, null, null, null, null, String.valueOf(1));


        if(c.getCount()>0)
        {
            c.moveToFirst();
            targetVal=c.getInt(0);
        }

       db.close();
        c.close();
        return targetVal;
    }



    public int getCurrent(){
        SQLiteDatabase db1 = this.getReadableDatabase();
        int currentVal=0;
        String[] columns = {COLUMN_CURRENT};
        Cursor c = db1.query(TABLE_STATS, columns, null, null, null, null, null, String.valueOf(1));

        if(c.getCount()>0)
        {
            c.moveToFirst();
            currentVal=c.getInt(0);
        }
        db1.close();
        c.close();
        return currentVal;
    }

    public void resetCurrent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_CURRENT, 0);
        db.update(TABLE_STATS, values1, COLUMN_ID + "=" + 1, null);
        db.close();
    }

    public void updateCurrent(int itemCal) {
        int current = itemCal + getCurrent();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_CURRENT, current);
        db.update(TABLE_STATS, values1, COLUMN_ID + "=" + 1, null);
        db.close();
    }

}
