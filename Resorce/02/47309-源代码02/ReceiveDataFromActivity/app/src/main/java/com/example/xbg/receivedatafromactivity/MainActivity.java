package com.example.xbg.receivedatafromactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  static int REQUEST_CODE=1011;//标识当前活动的请求
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SendDataBack.class);
                startActivityForResult(intent,REQUEST_CODE);//启动可返回结果的活动
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){//返回的请求码与当前活动请求码一致时，才执行后继操作
            if(resultCode==RESULT_OK){//RESULT_OK表示返回的活动已成功处理请求
                TextView tv= (TextView) findViewById(R.id.textView);
                //将另一个活动返回的Intent中的数据显示到文本视图中
                tv.setText("从另一个Activity返回的数据是："+data.getStringExtra("data"));
            }
        }
    }
}
