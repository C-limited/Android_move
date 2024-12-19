package com.example.xbg.priorityorderbroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class MyReceiver1 extends BroadcastReceiver {
    public MyReceiver1() {}
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver1接收到广播！");
        abortBroadcast();//终止广播传递
    }
}
