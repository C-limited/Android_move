package com.example.xbg.usehttpurlconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        HttpURLConnection con=null;
        BufferedReader reader=null;
        try {
            URL url=new URL("https://www.sina.com.cn");
            con=(HttpURLConnection)url.openConnection();
            //con.setDoOutput(true);
           // con.setChunkedStreamingMode(0);
            con.setRequestMethod("POST");
            //DataOutputStream out=new DataOutputStream(con.getOutputStream());
            //out.writeBytes("data=123");
            //con.setConnectTimeout(6000);
            //con.setReadTimeout(6000);
            InputStream in=con.getInputStream();
            reader =new BufferedReader(new InputStreamReader(in));
            StringBuilder result=new StringBuilder();
            String s;
            s=reader.readLine();
            while(s!=null){
                result.append(s);
                s=reader.readLine();
            }
            showResult(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null) reader.close();
                if(con!=null)con.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
