package com.example.xianhua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xianhua.adapter.RecordAdapter;
import com.example.xianhua.database.DBRecord;
import com.example.xianhua.entity.Record;

import java.util.ArrayList;

public class MyRecord extends AppCompatActivity {
    ListView listview;
    TextView empty;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_record);
        listview= findViewById(R.id.list_view);
        empty= findViewById(R.id.empty);
        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        set();
    }
    void set(){
        DBRecord db=new DBRecord(this);
        ArrayList<Record> array=db.getAll(Login.user.getName());
        for (Record t:
                array) {
            Log.d("--------",t.toString());
        }
        if(array.size()==0){
            empty.setVisibility(View.VISIBLE);
        }else{
            empty.setVisibility(View.GONE);
        }
        RecordAdapter adapter=new RecordAdapter(this,R.layout.layout_record,array);
        listview.setAdapter(adapter);
    }
}