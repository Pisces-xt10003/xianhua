package com.example.xianhua.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.xianhua.entity.User;

public class DBUser extends SQLiteOpenHelper {
    public DBUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private SQLiteDatabase dp;
    public DBUser(Context context){
        super(context,"users1",null,1);
        dp=this.getWritableDatabase();
    }
    public boolean add(User u ){
        ContentValues values=new ContentValues();
        values.put("name",u.getName());
        values.put("psw",u.getPsw());
        values.put("address",u.getAddress());
        long i=dp.insert("users",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        return false;
    }

    public  boolean change(User u){
        ContentValues values=new ContentValues();

        values.put("address",u.getAddress());
        long i=dp.update("users",values,"name=?",new String[]{u.getName()});
        if(i>0){
            Log.d("","修改成功");
            return true;
        }
        return false;
    }
    public boolean check(String n,String p){

        Cursor cursor=dp.query("users",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("name"));
            @SuppressLint("Range") String psw=cursor.getString( cursor.getColumnIndex("psw"));
            if(n.equals(name) && p.equals(psw)){
                return true;
            }
        }
        return false;
    }
    public User get(String n ){
        User u=new User();
        Cursor cursor=dp.query("users",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("name"));
            @SuppressLint("Range") String psw=cursor.getString( cursor.getColumnIndex("psw"));
            @SuppressLint("Range") String address=cursor.getString( cursor.getColumnIndex("address"));
            if(n.equals(name) ){
                u=new User(name,psw,address);
                break;
            }
        }
        return u;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table users(name text primary key,psw text not null,address text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
