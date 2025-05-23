package com.example.xianhua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xianhua.database.DBUser;
import com.example.xianhua.entity.User;

import java.util.Random;

public class Login extends AppCompatActivity {
    public static User user=new User();
    Button login;
    CheckBox remember;
    TextView register;
    private ImageView ivPasswordShow;
    EditText name,psw,number;
    String num="1234";
    SharedPreferences sharedPreferences;
    String getNumber(){
        String str="abcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<4;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        remember=findViewById(R.id.remember);
        psw=findViewById(R.id.psw_);

        name=findViewById(R.id.name_);

        register=findViewById(R.id.register);
        ivPasswordShow = findViewById(R.id.iv_password_show);
        sharedPreferences = getSharedPreferences("MyAccount",MODE_PRIVATE);
        name.setText(sharedPreferences.getString("username",null));
        psw.setText(sharedPreferences.getString("password",null));
        if(  name.getText().toString().isEmpty()){
            remember.setChecked(false);
        }else{
            remember.setChecked(true);
        }

        user=new User();
        DBUser db=new DBUser(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                }
                String n=name.getText().toString();
                String p=psw.getText().toString();
                if(db.check(n,p)){
                    //Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    user=db.get(n);
                    if(remember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",name.getText().toString());
                        editor.putString("password",psw.getText().toString());
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",null);
                        editor.putString("password",null);
                        editor.commit();
                    }
                    Intent intent = new Intent();
                    intent.setClass(Login.this ,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Login.this ,Register.class);
                startActivity(intent);
            }
        });

        ivPasswordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod method = psw.getTransformationMethod();
                if (method.equals(HideReturnsTransformationMethod.getInstance())) {
                    ivPasswordShow.setImageResource(R.mipmap.ic_password_close);
                    psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivPasswordShow.setImageResource(R.mipmap.ic_password_open);
                    psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        num=getNumber();

    }
}