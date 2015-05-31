/*
	App Name: Gute-Mathe-Fragen
	App URI: https://play.google.com/store/apps/details?id=de.gute_mathe_fragen
	App Description: This app connects to our math forum and shows the website (mobile interface) within a webview, it also reads the RSS feeds to display all questions and meta data.
	App Version: 1.0
	App Date: 2013-03-08
	App Authors: blackfire185, echteinfachtv
	App Author URI: http://www.echteinfach.tv/
	App License: GPLv3

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version. If you use the source code, you 
	have to keep the copyright notice in your files plus the author name. 

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

	See the GNU General Public License for more details:
	http://www.gnu.org/licenses/gpl.html
*/
package de.gute_mathe_fragen;


import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


public class WebViewseite extends Activity {
	private static final String DEBUG_TAG = "gmf-debug";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(DEBUG_TAG, "WebView onCreate()");
		setContentView(R.layout.activity_web_viewseite);
	
		String url = "http://gateoverflow.in/";
		
		WebView wv1 = (WebView)findViewById(R.id.webView1);
		wv1.getSettings().setJavaScriptEnabled(true);
		wv1.loadUrl(url);
		Log.d(DEBUG_TAG, "WebView Lade: http://gateoverflow.in/");
		wv1.setWebViewClient(new WebViewClient());
		 final Activity MyActivity = this;
		 final ProgressBar progess = (ProgressBar) findViewById(R.id.progressBar1);
		 
	       wv1.setWebChromeClient(new WebChromeClient() {
	        public void onProgressChanged(WebView view, int progress)   //f�r Ladebalken
	        {
	        	if (progress <100){ //Seite am laden
	        		progess.setVisibility(View.VISIBLE);
	        	}else{	//Seite fertig geladen
	        		progess.setVisibility(View.GONE);
	        		Log.d(DEBUG_TAG, "WebView Seite geladen");
	        	}
	         
	         progess.setProgress(progress); 
	        
	         // Return the app name after finish loading
	            
	          }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_web_viewseite, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		WebView wv1 = (WebView)findViewById(R.id.webView1);	
	    switch (item.getItemId()) {
	        case R.id.aktualisieren:	        	
	        	wv1.reload();
	        	Log.d(DEBUG_TAG, "WebView Reload");
	            return true;
	        case R.id.impressum:
	        	wv1.loadUrl("http://gateoverflow.in/qa");
	        	Log.d(DEBUG_TAG, "WebView Lade: http://gateoverflow.in/qa");
	        	return true;
	        case R.id.home:
	        	wv1.loadUrl("http://gateoverflow.in/qa");
	        	Log.d(DEBUG_TAG, "WebView Lade: http://gateoverflow.in/qa");
	        	return true;
	        case R.id.service:
	       	 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WebViewseite.this);
	        	Editor ed = prefs.edit();
	        	  if (prefs.getBoolean("antwortcheck", false)) {//Service l�uft
	        		  ed.putBoolean("antwortcheck", false);
	        		  Toast.makeText(WebViewseite.this, "Service wurde gestoppt", Toast.LENGTH_LONG).show();
	        	  }else{//Service l�uft nicht
	        		  ed.putBoolean("antwortcheck", true);
	        		  Toast.makeText(WebViewseite.this, "Service wurde gestartet", Toast.LENGTH_LONG).show();
	        	  }
	        	ed.commit();
	        	Intent intent = new Intent();
	    		intent.setAction("de.gute_mathe_fragen.servicestarten");
	    		sendBroadcast(intent); 
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    }
	
	 final Activity MyActivity = this;
	private class MyWebViewClient extends WebViewClient {
		
		
		 
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	WebView wv1 = (WebView)findViewById(R.id.webView1);
	    	wv1.loadUrl(Uri.parse(url).getHost());
	        
	        return true;
	        
	    }
	    
	    
	    
	}
        
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
		WebView myWebView = (WebView)findViewById(R.id.webView1);
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}

	
	protected void onResume(){
		super.onResume();
		WebView wv1 = (WebView)findViewById(R.id.webView1);
		MainActivity tabAct = (MainActivity)getParent();
	   String url =  tabAct.getURL();  
		wv1.loadUrl(url);
		Log.d(DEBUG_TAG, "WebView onResume(), Lade: " + url);
		
	}
	
	protected void onPause(){
		super.onPause();
		WebView wv1 = (WebView)findViewById(R.id.webView1);
		MainActivity tabAct = (MainActivity)getParent();
		tabAct.setURL(wv1.getOriginalUrl());
	}
}
