package de.arjun;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by arjun on 01/06/15.
 */
public class myWebClient extends WebViewClient
{
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub

        view.loadUrl(url);
        return true;

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);

       // progressBar.setVisibility(View.GONE);
    }
}

