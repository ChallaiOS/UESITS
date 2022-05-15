package com.mysuperride.developerdev.uesi_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import java.net.URISyntaxException;


public class WebviewActivity extends AppCompatActivity {

    WebView web;
//    Bundle extras = getIntent().getExtras();
//    String myEtText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

//        if (extras != null) {
//            myEtText = getIntent().getExtras().getString("edittextvalue");
//
//        }

        Intent intent = getIntent();
        String str = intent.getStringExtra("edittextvalue");

        // Get a reference to our WebView object
        web = findViewById(R.id.webView);
        // Create a WebSettings object
        WebSettings webSettings = web.getSettings();
        // For using Zoom feature
        // webSettings.setBuiltInZoomControls(true);
        // Enable JavaScript using the WebSettings object
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        web.loadUrl(str);

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            // By returning false you're telling Android that, this is my website, so, do not override;
            // let WebView load the page.
            return false;
        }

        /*
        Additional code.
        Sometimes the WebView can't deal with special URL scheme other than http or
        https, for example intent://, app://, tel:, mailto:, whatsapp:// etc.
        So, we are required to override shouldOverrideUrlLoading method in
        the custom WebViewClient class that we created, called Callback, and
        handle these schemes or to disable these schemes. The solution that works
        in your case is given below. You can add or remove few if blocks if you need to.
        Enjoy!
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http") || url.startsWith("https")) {
                return false;
            }
            if (url.startsWith("intent")) {
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                    if (fallbackUrl != null) {
                        view.loadUrl(fallbackUrl);
                        return true;
                    }
                } catch (URISyntaxException e) {
                    // Syntax problem with uri
                }
            }
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(intent);
            } else if (url.startsWith("mailto:")) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                startActivity(intent);
            }
            view.loadUrl(url);
            return true;
        }
    }



    // Additional code.
    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }


}