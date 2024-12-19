package com.example.xbg.priorityorderbroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {}
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver2接收到广播！");
    }
}
