package com.example.xbg.startonboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    private static String BOOT_ACTION="android.intent.action.BOOT_COMPLETED";
    public BootReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"bootup",Toast.LENGTH_SHORT).show();
        String action=intent.getAction();
        if(action.equals(BOOT_ACTION) ){
            Intent boot=new Intent(context,MainActivity.class);//定义用于启动活动的Intent
            boot.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(boot);
        }
    }
}
