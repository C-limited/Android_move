package com.example.launchlocalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class LocalAppAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_app_aty);
        Uri uri = getIntent().getData();
        TextView txt = (TextView)findViewById(R.id.textView2);
        String suri = Uri.decode(uri.toString());
        String data = uri.getQueryParameter("data");
        txt.setText("\n下面是URI和查询参数：\n"+suri+"\n查询参数data="+data);
    }
}