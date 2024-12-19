package com.example.pad.launchlocalapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LocalAppAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_app_aty);
        Uri uri=getIntent().getData();//获得URI字符串
        TextView txt=(TextView)findViewById(R.id.textView2);
        String suri=Uri.decode(uri.toString());//解码URI字符串
        String data=uri.getQueryParameter("data");//获得URI中的查询参数
        txt.setText("\n下面是URI和查询参数：\n"+suri+"\n查询参数data="+data);
    }
}
