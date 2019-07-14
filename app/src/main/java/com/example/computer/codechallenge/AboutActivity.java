package com.example.computer.codechallenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        webView = findViewById(R.id.webView);
        pb = findViewById(R.id.progress_bar);

        checkNetworkState();

        // Checking saved state instance
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
            pb.setVisibility(View.GONE);
            webView.getSettings().setJavaScriptEnabled(true);
            webSettings();
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webSettings();
            webView.loadUrl("https://andela.com/alc/");
        }
    }

    // Enable navigation in the WebView
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Saving instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    // Checking Network Status
    private void checkNetworkState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            Button retry = findViewById(R.id.button_retry);
            Group noNetworkGroup = findViewById(R.id.group);
            noNetworkGroup.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            retry.setOnClickListener(view -> {
                recreate();
                webView.getSettings().setJavaScriptEnabled(true);
                webSettings();
            });
        }
    }

    // Setting WebView client and overriding SSL error
    private void webSettings() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.GONE);
            }
        });
    }
}
