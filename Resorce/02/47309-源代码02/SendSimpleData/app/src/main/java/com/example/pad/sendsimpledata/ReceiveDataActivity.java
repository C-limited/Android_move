package com.example.pad.sendsimpledata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);
        TextView tv= (TextView) findViewById(R.id.textView2);
        //从Intent对象获取简单数据
        Intent intent=getIntent();
        //String name=intent.getStringExtra("name");
        //int age=intent.getIntExtra("age",0);
        
        //从Intent对象获取Bundle对象
        Bundle bd=intent.getExtras();
        String name=bd.getString("name");
        int age=bd.getInt("age");
        tv.setText("接收到的数据如下：\nname="+name+"\nage="+age);
    }
}
