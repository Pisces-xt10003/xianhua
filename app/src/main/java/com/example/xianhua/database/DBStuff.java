package com.example.xianhua.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.xianhua.entity.Stuff;

import java.util.ArrayList;

public class DBStuff extends SQLiteOpenHelper {
    private SQLiteDatabase dp;

    public DBStuff(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBStuff(Context context){
        super(context, "Stuffs1", null, 1);
        dp = this.getWritableDatabase();
    }

    public boolean add(Stuff s){
        ContentValues values = new ContentValues();

        values.put("name", s.getName());
        values.put("title", s.getTitle());
        values.put("kind", s.getKind());
        values.put("price", s.getPrice());

        long i = dp.insert("Stuff", null, values);
        if (i > 0) {
            Log.d("", "插入成功");
            return true;
        }
        Log.d("", "插入失败");
        return false;
    }


    public ArrayList<Stuff> getAll(){
        ArrayList<Stuff> array = new ArrayList<>();
        Cursor cursor = dp.query("Stuff", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String kind = cursor.getString(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));

            Stuff u = new Stuff(String.valueOf(id), name, title, kind, price);
            array.add(u);
        }
        return array;
    }

    public Stuff getById(int _id){
        Stuff s = new Stuff();
        Cursor cursor = dp.query("Stuff", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String kind = cursor.getString(cursor.getColumnIndex("kind"));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
            if(id == _id){
                s = new Stuff(String.valueOf(id), name, title, kind, price);
                break;
            }
        }
        return s;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Stuff (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, title TEXT NOT NULL, kind TEXT NOT NULL, price TEXT NOT NULL, image_res_id INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
