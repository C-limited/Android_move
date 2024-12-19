package com.example.administrator.classschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static String[] week={"星期一","星期二","星期三","星期四","星期五"};
    private static String[] nos={"第1节","第2节","第3节","第4节","第5节","第6节","第7节"};
    private static String[] times={"08:00-08:50","09:00-09:50","10:00-10:50",
            "11:00-11:50","14:00-14:50","15:00-15:50","16:00-16:50"};
    private List<MyClass> schedule=null;//课程安排列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化星期下拉列表Spinner
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, week);
        Spinner spWeek=(Spinner)findViewById(R.id.spWeek);
        spWeek.setAdapter(adapter);
        spWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                new Thread(new Runnable() {//在新线程中完成HTTP请求
                    @Override
                    public void run() {
                        showClass(doUrlGet(position));//选择星期几时，显示课程安排
                    }
                }).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    private String doUrlGet(int index){//使用OkHttp获取服务器端课程安排信息的JSON数据
        try {
            OkHttpClient okClient=new OkHttpClient();
            Request.Builder builder=new Request.Builder();
            builder.url("http://192.168.0.104/getclass.asp?index="+index);
            Request request=builder.build();
            Response response=okClient.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    private void showClass(final String data){
        runOnUiThread(new Runnable() {//返回主线程
            @Override
            public void run() {
                schedule=new ArrayList<MyClass>();
                //初始化课程信息安排，每天7节课，节次、时间固定，课程名称初始化为空
                for(int i=0;i<7;i++)
                    schedule.add(new MyClass(nos[i],times[i],""));
                try {//解析JSON字符串中的课程安排信息，按节次将课程名称补充完整
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String cname;
                        JSONObject item = jsonArray.getJSONObject(i);
                        int n=item.getInt("no");
                        cname=item.getString("name");       //获得课程名称
                        schedule.get(n-1).setName(cname);   //修改列表中的名称名称
                    }
                    ClassAdapter adapter=new ClassAdapter(MainActivity.this,
                            R.layout.classitem,schedule);//创建适配器
                    ListView listView=(ListView)findViewById(R.id.lvClass);
                    listView.setAdapter(adapter);//用适配器填充课程安排ListView
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
