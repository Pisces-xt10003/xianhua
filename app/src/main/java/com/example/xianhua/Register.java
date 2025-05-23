package com.example.xianhua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xianhua.database.DBUser;
import com.example.xianhua.entity.User;


public class Register extends AppCompatActivity {
    TextView register_back;
    EditText name,address;
    EditText _psw;
    EditText re_psw;
    Button join_now;
    private ImageView ivPasswordShow, ivPasswordShowAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setContentView(R.layout.activity_register);
        address=findViewById(R.id.address);
        register_back=findViewById(R.id.register_back);
        name=findViewById(R.id.phone);
        _psw=findViewById(R.id._psw);
        re_psw=findViewById(R.id.re_psw);
        ivPasswordShow = findViewById(R.id.iv_password_show);
        ivPasswordShowAgain = findViewById(R.id.iv_password_show_again);
        join_now=findViewById(R.id.join_now);
        join_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(_psw.getText().toString().isEmpty() ||
                        re_psw.getText().toString().isEmpty() ||
                        name.getText().toString().isEmpty()||
                        address.getText().toString().isEmpty() ){
                    Toast.makeText(Register.this, "数据不能为空", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!_psw.getText().toString().equals(re_psw.getText().toString())){
                    Toast.makeText(Register.this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                    return ;
                }
                DBUser db=new DBUser(getBaseContext());
                User u=new User(name.getText().toString(),_psw.getText().toString(),address.getText().toString());
                if(db.add(u)){
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Register.this, "注册失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivPasswordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod method = _psw.getTransformationMethod();
                if (method.equals(HideReturnsTransformationMethod.getInstance())) {
                    ivPasswordShow.setImageResource(R.mipmap.ic_password_close);
                    _psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivPasswordShow.setImageResource(R.mipmap.ic_password_open);
                    _psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        ivPasswordShowAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod method = re_psw.getTransformationMethod();
                if (method.equals(HideReturnsTransformationMethod.getInstance())) {
                    ivPasswordShowAgain.setImageResource(R.mipmap.ic_password_close);
                    re_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivPasswordShowAgain.setImageResource(R.mipmap.ic_password_open);
                    re_psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

}