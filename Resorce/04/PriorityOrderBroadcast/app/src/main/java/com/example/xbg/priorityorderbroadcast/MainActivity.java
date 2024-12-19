package com.example.xbg.priorityorderbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendMyBroadcast(View view){
        //发送广播
        Intent  intent=new Intent("com.example.xbg.priorityorderbroadcast.ACTION");
        //sendBroadcast(intent);
        sendOrderedBroadcast(intent,null);//发送有序广播
    }
}
