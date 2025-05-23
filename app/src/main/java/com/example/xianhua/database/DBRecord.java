package com.example.xianhua.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.xianhua.entity.Record;

import java.util.ArrayList;

public class DBRecord extends SQLiteOpenHelper {
    public DBRecord(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private SQLiteDatabase dp;
    public DBRecord(Context context){
        super(context,"Records1",null,1);
        dp=this.getWritableDatabase();
    }
    public boolean add(Record s){
        ContentValues values=new ContentValues();
        values.put("username",s.getUsername());
        values.put("id",s.getId());
        values.put("name",s.getName());
        values.put("price",s.getPrice());
        values.put("address",s.getAddress());
        long i=dp.insert("Record",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        Log.d("","插入失败");
        return false;
    }

    public ArrayList<Record> getAll(String user){
        ArrayList<Record> array=new ArrayList();
        Cursor cursor=dp.query("Record",null,null,null,null,null,null);
        while(cursor.moveToNext()){

            @SuppressLint("Range") String username=cursor.getString( cursor.getColumnIndex("username"));
            @SuppressLint("Range") String id=cursor.getString( cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("name"));
            @SuppressLint("Range") String address=cursor.getString( cursor.getColumnIndex("address"));
            @SuppressLint("Range") String price=cursor.getString( cursor.getColumnIndex("price"));
            if(user.equals(username)){
                Record u=new Record(username,id,name ,price,address);
                array.add(u);
            }
        }
        return array;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table Record(username text not null,id text not null,name text not null,price text not null,address text not null)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

