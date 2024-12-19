package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;//在上面的代码中，NOTIFICATION_ID 被定义为一个静态常量，并设置为 1。你可以根据实际需要选择一个合适的值来标识通知。请确保在其他地方不要使用相同的值作为其他通知的标识符，以免造成冲突。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.drawable.smallico);
                builder.setContentTitle("嗨，你有一个新消息！");
                builder.setContentText("你已经学会了创建Notification了。");
                builder.setAutoCancel(true);
                Intent resultIntent=new Intent(MainActivity.this,NotificationActivity.class);
                TaskStackBuilder stackBuilder=TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(NotificationActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                Notification notification=builder.build();//创建Notification对象
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(NOTIFICATION_ID,notification);
            }
        });
    }
}