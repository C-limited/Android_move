package com.example.sendobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView tv = (TextView) findViewById(R.id.textView2);
        String name = user.getName();
        int age = user.getAge();
        tv.setText("接收到的User对象： User(name:"+name+",age:"+age+")");
    }
}