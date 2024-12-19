package com.example.administrator.parsejson;

import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvJson=(TextView)findViewById(R.id.tvJson);
        Button btGetJson=(Button)findViewById(R.id.btGetJson);
        btGetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击按钮时获取JSON数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient okClient=new OkHttpClient();
                            Request.Builder builder=new Request.Builder();
                            builder.url("http://192.168.0.104/getjson.json");
                            builder.addHeader("charset","utf-8");
                            Request request=builder.build();
                            Response response=okClient.newCall(request).execute();
                            String data=response.body().string();
                            data=new String(data.getBytes(),"utf-8");
                            showResult(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        Button btParse=(Button) findViewById(R.id.btParse);
        btParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击按钮时解析XML文档
                TextView tvParse= (TextView) findViewById(R.id.tvParse);
                tvParse.setText(JsonData(tvJson.getText().toString()));
            }
        });
    }
    private void showResult(final String result){
        runOnUiThread(new Runnable() {//返回主线程
            @Override
            public void run() {
                tvJson.setText(result);//在TextView中显示JSON数据
            }
        });
    }
    private String JsonData(String data){
        try {
            JSONObject json=new JSONObject(data);
            String result="jike="+json.getString("jike")+"\n";//获得指定键的值
            JSONArray users=json.getJSONArray("users");//获得指定键的数组
            for(int i=0;i<users.length();i++){
                JSONObject item=users.getJSONObject(i);//获得一个数组元素
                result+="user"+(i+1)+"     id="+item.getString("id");//获取键值
                result+="   password="+item.getString("password")+"\n";
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
