package com.example.sting.limaguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Displays a WebView that dislays the correct reviews website
 * URL to be shown was passed through an intent
 */
public class ReviewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar mProgressBar;
    private TextView urlTexTView;
    private ImageView backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Getting URL passed through intent
        Intent intent = getIntent();
        String reviewURL = intent.getStringExtra("reviewURL");

        // Getting action bar to set custom view, gives the loading effect feature every time
        // a URL is loaded
        ActionBar actionBar = getSupportActionBar();
        View customView = getLayoutInflater().inflate(R.layout.review_action_bar, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customView);

        // Creating a toolbar to eliminate unnecessary spacing on the sides of customView
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setPadding(0, 0, 0, 0);
        parent.setContentInsetsAbsolute(0, 0);

        // Adding settings to toolbar items
        urlTexTView = actionBar.getCustomView().findViewById(R.id.url_text_view);
        urlTexTView.setText(reviewURL);

        backImageView = actionBar.getCustomView().findViewById(R.id.back_image_view);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()){
                    webView.goBack();
                } else {
                    onBackPressed();
                }
            }
        });

        mProgressBar = actionBar.getCustomView().findViewById(R.id.progressbar);

        // Initializing webView
        webView = findViewById(R.id.review_web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // WebViewClient allows you to execute code when events happen during the loading of a URL
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Make progressbar visible as soon as page starts loading
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

        });

        // WebChromeClient allows you to execute code when events happen during the loading of a URL,
        // it also allows you to catch javascript events executed inside webView
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, final int newProgress) {
                // Update the progress bar with page loading progress
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar once page is done loading
                    mProgressBar.setVisibility(View.GONE);
                    urlTexTView.setText(webView.getTitle());
                }
            }
        });
        webView.loadUrl(reviewURL);
    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
