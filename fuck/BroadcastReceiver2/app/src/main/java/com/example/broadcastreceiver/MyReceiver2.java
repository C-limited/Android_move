package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {
    public static String ACTION="learnbroadcastreceiver2.MyReceiver2";
    public MyReceiver2() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"收到一个广播消息",Toast.LENGTH_LONG).show();
    }
}