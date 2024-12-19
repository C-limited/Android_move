package com.example.xbg.useokhttp;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doUrlGet();
            }
        }).start();
    }
    private void doUrlGet(){
        try {
            OkHttpClient okClient=new OkHttpClient();
            Request.Builder builder=new Request.Builder();
            builder.url("http://www.baidu.com");
            /*RequestBody requestBody=new FormBody.Builder()
                    .add("id","admin")
                    .add("password","123")
                    .build();
            builder.post(requestBody);*/
            Request request=builder.build();
            Response response=okClient.newCall(request).execute();
            String result=response.body().string();
            showResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showResult(final String result){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(result);
            }
        });
    }
}
