package com.example.xianhua.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBCart extends SQLiteOpenHelper {
    public DBCart(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private SQLiteDatabase dp;
    public DBCart(Context context){
        super(context,"DBCart1",null,1);
        dp=this.getWritableDatabase();
    }
    public boolean add(String id,String username){
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("username",username);
        long i=dp.insert("cart",null,values);
        if(i>0){
            Log.d("","插入成功");
            return true;
        }
        return false;
    }
    public boolean del(String id,String username){

        long i=dp.delete("cart","id=? and username=?",new String[]{id,username});
        if(i>0){
            Log.d("","删除成功");
            return true;
        }
        return false;
    }
    public ArrayList<String> getLikesTitle(String username){
        ArrayList<String> array=new ArrayList();

        Cursor cursor=dp.query("cart",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String id=cursor.getString( cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name=cursor.getString( cursor.getColumnIndex("username"));
            if(name.equals(username)){
                array.add(id );
            }
        }
        return array;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table cart(id text not null,username text not null,PRIMARY KEY(id,username))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
