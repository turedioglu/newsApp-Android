package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detailed  extends AppCompatActivity {

    TextView txtTitle, txtDescription, txtContent, txtPubDate;
    WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);


       /*
       Bu kısım detail ekranında native olarak göstermek istedigimiz yerlerin tanımlaması
       txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
       txtContent = findViewById(R.id.txtContent);
        txtPubDate = findViewById(R.id.txtPubDate); */

        webview = findViewById(R.id.webView);


        Intent intent = getIntent();
      /*
        Bu kısım main ekranından hangi bilgiler geliyorsa onu kabul ediyor
        String title =intent.getStringExtra("title");
        String pubDate =intent.getStringExtra("pubDate");
        String content =intent.getStringExtra("content");
        String description = intent.getStringExtra("description"); */

        String url =intent.getStringExtra("url");

        /*
        Bu kısım gelen bilgileri yazdırır yukardaki kod bloguyla baglantılıdır
        txtTitle.setText(title);
        txtPubDate.setText(pubDate);
        txtDescription.setText(description);
        txtContent.setText(content); */

        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

    }
}
