package com.example.xbg.launchsingletaskandinstance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        System.out.println( "任务ID："+getTaskId()+"\n"+this.toString()+"正在创建！");
        TextView tv= (TextView) findViewById(R.id.textView);
        tv.setText(String.format("任务ID：%d\n活动实例：%s",getTaskId(),this.toString()));
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BActivity.this,BActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println( "任务ID："+getTaskId()+"\n"+this.toString()+"已经销毁！");
    }
}
