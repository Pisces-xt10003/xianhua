package com.example.xianhua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.xianhua.database.DBStuff;
import com.example.xianhua.entity.Stuff;
import com.example.xianhua.js.BlankFragment_js_Main;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FrameLayout main;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        main = findViewById(R.id.main);
        navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        BlankFragment_Main fragment3 = BlankFragment_Main.newInstance();
                        FragmentTransaction tran3 = getSupportFragmentManager().beginTransaction();
                        tran3.replace(R.id.main, fragment3);
                        tran3.commit();
                        return true;
                    case R.id.cart:
                        BlankFragment_Cart fragment2 = BlankFragment_Cart.newInstance();
                        FragmentTransaction tran2 = getSupportFragmentManager().beginTransaction();
                        tran2.replace(R.id.main, fragment2);
                        tran2.commit();
                        return true;
                    case R.id.me:
                        BlankFragment_me fragment1 = BlankFragment_me.newInstance();
                        FragmentTransaction tran1 = getSupportFragmentManager().beginTransaction();
                        tran1.replace(R.id.main, fragment1);
                        tran1.commit();
                        return true;
                    case R.id.js:
                        BlankFragment_js_Main fragment4 = BlankFragment_js_Main.newInstance();
                        FragmentTransaction tran4 = getSupportFragmentManager().beginTransaction();
                        tran4.replace(R.id.main, fragment4);
                        tran4.commit();
                        return true;
                }
                return false;
            }
        });
        navigation.setSelectedItemId(R.id.home);
    }

    void init() {
        DBStuff db = new DBStuff(this);
        ArrayList<Stuff> list = db.getAll();
        for (Stuff t : list) {
            Log.d("--------", t.toString());
        }
        if (list.size() != 0) {
            return;
        }

        // 创建并添加梦婉百合
        Stuff s = new Stuff("梦婉百合", "百合低语，轻诉时光温柔。粉白交织，似梦幻旋律漫舞，为生活缀满纯净美好。生日的欢悦、日常的温馨，亦或对亲友的美好祝福，这束百合，皆是对美好时刻的深情致意，让每一次凝视，都邂逅心灵的宁静与诗意。", "百合", "88");
        db.add(s);

        // 创建并添加晴悦雏菊
        s = new Stuff("晴悦雏菊", "雏菊绽放，阳光倾洒其间。黄白相间的绚烂，如青春活力跃动，似快乐音符流淌。赠予好友，传递纯真情谊；装点居室，点亮平凡时光。每一朵雏菊，都是生活的小确幸，让美好时刻纷至沓来，绽放无尽欢颜。", "雏菊", "99");
        db.add(s);

        // 创建并添加柔霞粉玫
        s = new Stuff("柔霞粉玫", "粉玫轻舞，爱意绵绵不绝。柔和粉色，是浪漫私语，是温柔拥抱，藏着心底最深的眷恋。情人节的炽烈、纪念日的温馨、亦或勇敢的表白时刻，这束粉玫瑰，诉说着深情厚意，让每一个瞬间都凝成永恒的甜蜜回忆，沉醉于爱与美的温柔乡。", "玫瑰", "188");
        db.add(s);
    }
}