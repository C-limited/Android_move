package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyReceiver2 receiver=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void registerMyReceiver(View view){
        //注册广播接收器
        if(receiver==null){
            receiver=new MyReceiver2();
            registerReceiver(receiver,new IntentFilter(MyReceiver2.ACTION));
        }
    }
    public  void  unRegisterMyReceiver(View view){
        //注销广播接收器
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }
    public void sendMsg(View view){
        sendBroadcast(new Intent(MyReceiver2.ACTION));
    }
}