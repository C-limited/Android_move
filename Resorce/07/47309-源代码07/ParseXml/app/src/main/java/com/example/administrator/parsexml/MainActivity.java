package com.example.administrator.parsexml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvXml= (TextView) findViewById(R.id.tvXml);
        Button btGetXml= (Button) findViewById(R.id.btGetXml);
        btGetXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击按钮时通过HTTP请求获取XML文档
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doUrlGet();
                    }
                }).start();
            }
        });
        Button btDomXml=(Button) findViewById(R.id.btDomXml);
        btDomXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击按钮时解析XML文档
                TextView tvDomResult= (TextView) findViewById(R.id.tvDomResult);
                tvDomResult.setText(domXml());
            }
        });
        Button btPullXml=(Button) findViewById(R.id.btPullXml);
        btPullXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击按钮时解析XML文档
                TextView tvPullResult= (TextView) findViewById(R.id.tvPullResult);
                tvPullResult.setText(pullXml());
            }
        });
    }
    private void doUrlGet(){//使用OkHttp获取XML文档
        try {
            OkHttpClient okClient=new OkHttpClient();
            Request.Builder builder=new Request.Builder();
            builder.url("http://192.168.0.104/getxml.xml");
            Request request=builder.build();
            Response response=okClient.newCall(request).execute();
            showResult(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showResult(final String result){
        runOnUiThread(new Runnable() {//返回主线程
            @Override
            public void run() {
                tvXml.setText(result);//在TextView中显示XML文档
            }
        });
    }
    private String pullXml(){
        try{
            String xmlData=tvXml.getText().toString();
            XmlPullParserFactory xmlFactory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=xmlFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int event=xmlPullParser.getEventType(); //获得当前事件类型
            String result="",nodeName="";
            while(event!=xmlPullParser.END_DOCUMENT){
                nodeName=xmlPullParser.getName();//获得标签名称
                if(event==xmlPullParser.START_TAG){
                    if(nodeName.equals("id"))
                        result+="id="+xmlPullParser.nextText()+"\n";//获得标签文本进行处理
                    else if(nodeName.equals("password"))
                        result+="password="+xmlPullParser.nextText()+"\n";
                }
                event=xmlPullParser.next();//获得下一个事件类型
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
    private String domXml(){//使用DOM解析XML文档
        try{
            String xmlData=tvXml.getText().toString();
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            InputSource data= new InputSource(new ByteArrayInputStream(xmlData.getBytes("UTF-8")));
            Document document=builder.parse(data);
            Element root=document.getDocumentElement();
            NodeList nodes=root.getElementsByTagName("user");
            String result="";
            for (int i=0;i<nodes.getLength();i++){
                Element user=(Element)nodes.item(i);
                Element id=(Element)user.getElementsByTagName("id").item(0);
                Element password=(Element) user.getElementsByTagName("password").item(0);
                result+="id="+id.getTextContent();
                result+="\npassword="+password.getTextContent();
                result+="\n";
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
