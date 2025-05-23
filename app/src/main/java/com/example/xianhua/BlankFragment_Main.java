package com.example.xianhua;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xianhua.adapter.AdPagerAdapter;
import com.example.xianhua.adapter.StuffAdapter;
import com.example.xianhua.database.DBStuff;
import com.example.xianhua.entity.Stuff;

import java.util.ArrayList;


public class BlankFragment_Main extends Fragment {
    private ViewPager mViewPager;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            int itemCount = mViewPager.getAdapter().getCount();
            int nextItem = (currentItem + 1) % itemCount;
            mViewPager.setCurrentItem(nextItem);
            mHandler.postDelayed(this,3000); // 3秒后再次调用
        }
    };
    public BlankFragment_Main() {
        // Required empty public constructor
    }


    public static BlankFragment_Main newInstance( ) {
        BlankFragment_Main fragment = new BlankFragment_Main();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ListView listview;
    EditText content;
    ImageView search;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_blank__main, container, false);
        mViewPager = v.findViewById(R.id.view_pager);


        int[] adImages = {R.drawable.head1, R.drawable.h02};

        AdPagerAdapter adapter = new AdPagerAdapter(getActivity(), adImages);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 停止自动滚动
                        mHandler.removeCallbacks(mRunnable);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // 启动自动滚动
                        mHandler.postDelayed(mRunnable, 1000);
                        break;
                }
                return false;
            }
        });
        mViewPager.setAdapter(adapter);
        listview=v.findViewById(R.id.list_view);
        content=v.findViewById(R.id.content);
        search=v.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=content.getText().toString();
                setSearch(str);
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        set();
    }
    void setSearch(String str){
        DBStuff db=new DBStuff(getContext());
        ArrayList<Stuff> arrayTemp=db.getAll();
        ArrayList<Stuff> array=new ArrayList<>();
        for (Stuff t:
                arrayTemp) {
            if( t.getName().contains(str) ||t.getTitle().contains(str)){
                array.add(t);
            }
        }
        if(array.size()==0){
            Toast.makeText(getContext(), "未搜索到关键字商品", Toast.LENGTH_SHORT).show();
            array=arrayTemp;
        }
        StuffAdapter adapter=new StuffAdapter(getContext(),R.layout.layout_stuff,array);
        listview.setAdapter(adapter);
        ArrayList<Stuff> finalArray = array;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s=( finalArray.get(i)).getName();
                //Toast.makeText(getContext(), "name："+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("id",( finalArray.get(i)).getId());
                intent.setClass(getContext() , Detail.class);
                startActivity(intent);
            }
        });
    }
    void set(){
        DBStuff db=new DBStuff(getContext());
        ArrayList<Stuff> array=db.getAll();
        for (Stuff t:
                array) {
            Log.d("--------",t.toString());
        }
        StuffAdapter adapter=new StuffAdapter(getContext(),R.layout.layout_stuff,array);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s=( array.get(i)).getName();
                //Toast.makeText(getContext(), "name："+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("id",( array.get(i)).getId());
                intent.setClass(getContext() , Detail.class);
                startActivity(intent);
            }
        });
    }
}