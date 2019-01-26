package com.example.icog.mapsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

//
//        // find the WebView by name in the main.xml of step 2
//        WebView browser=(WebView)findViewById(R.id.contact);
//
//        // Enable javascript
//        browser.getSettings().setJavaScriptEnabled(true);
//
//        // Set WebView client
//        browser.setWebChromeClient(new WebChromeClient());
//
//        browser.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        // Load the webpage
//        browser.loadUrl("file:///android_asset/www/contactus.html");
//
//    }
}
}
