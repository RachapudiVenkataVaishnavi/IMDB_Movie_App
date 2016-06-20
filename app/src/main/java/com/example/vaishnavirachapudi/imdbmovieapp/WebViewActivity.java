package com.example.vaishnavirachapudi.imdbmovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    WebView web;
    String imdbID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        if(getIntent().getExtras()!=null){
            imdbID = "http://m.imdb.com/title/"+getIntent().getExtras().getString(MovieDetailActivity.IMAGE_KEY);
        }
        web = (WebView) findViewById(R.id.webView);
        web.loadUrl(imdbID);
        web.setWebViewClient(new WebViewClient());
    }
}
