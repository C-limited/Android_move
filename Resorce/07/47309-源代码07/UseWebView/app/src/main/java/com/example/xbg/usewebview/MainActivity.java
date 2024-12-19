package com.example.xbg.usewebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView= (WebView) findViewById(R.id.webView);
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);//启用JavaScript
        webView.setWebViewClient(new WebViewClient());//使页面导航保持在WebView中
        webView.loadUrl("https://www.sina.com.cn");
    }
}
