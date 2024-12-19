package com.example.loacalbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得当前本地广播管理器
        localBroadcastManager= LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter=new IntentFilter("MyLocalBroadcastReceiver");
        localReceiver=new MyReceiver();//创建广播接收器对象
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播接收器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);//注销本地广播接收器
    }

    public void sendMyBroadcst(View view){
        Intent  intent=new Intent("MyLocalBroadcastReceiver");//用注册的操作创建Intent
        localBroadcastManager.sendBroadcast(intent);
    }

    public static class MyReceiver extends BroadcastReceiver {
        public MyReceiver() { }

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到一个本地广播消息", Toast.LENGTH_LONG).show();
        }
    }
}
