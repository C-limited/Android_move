package com.example.sendsimpledata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class receiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        Intent intent = getIntent();
        TextView tv = (TextView)findViewById(R.id.textView2);
        String name = intent.getStringExtra("neme");
        int age = intent.getIntExtra("age",0);
        tv.setText("接收到的数据如下：\nname="+name+"\nage="+age);
    }
}