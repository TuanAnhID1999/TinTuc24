package com.example.administrator.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity{
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        webView = findViewById(R.id.web_view);
        getdata();
    }
    public void getdata() {
        Intent intent = getIntent();
        if(intent != null){
            String link = intent.getStringExtra("link");
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(link);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
    }
}
