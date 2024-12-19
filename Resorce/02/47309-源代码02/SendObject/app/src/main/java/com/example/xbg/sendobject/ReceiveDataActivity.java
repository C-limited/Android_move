package com.example.xbg.sendobject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        Intent intent=getIntent();
        //User user= (User) intent.getSerializableExtra("user");//类实现Serializable接口时用
        User user= (User) intent.getParcelableExtra("user");//类实现Parcelable接口时用
        TextView tv= (TextView) findViewById(R.id.textView2);
        String name=user.getName();
        int age=user.getAge();
        tv.setText("接收到的User对象：User(name:"+name+",age:"+age+")");
    }
}
