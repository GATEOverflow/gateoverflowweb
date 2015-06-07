/*
	App Name: Gute-Mathe-Fragen
	App URI: https://play.google.com/store/apps/details?id=de.gute_mathe_fragen
	App Description: This Android app connects to our math forum and shows the website (mobile interface) within a webview, it also reads the RSS feeds to display all questions and meta data.
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
package de.arjun;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	WebView web;
	ProgressBar progressBar;

	private ValueCallback<Uri> mUploadMessage;
	private final static int FILECHOOSER_RESULTCODE=1;

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
									Intent intent) {
		if(requestCode==FILECHOOSER_RESULTCODE)
		{
			if (null == mUploadMessage) return;
			Uri result = intent == null || resultCode != RESULT_OK ? null
					: intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		}
	}
	String url = "http://gateoverflow.in/qa";
private static final String DEBUG_TAG = "gmf-debug";
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("first").setIndicator("Website"/*,getResources().getDrawable(R.drawable.home)*/).setContent(new Intent(this  ,WebViewseite.class )));
        mTabHost.addTab(mTabHost.newTabSpec("second").setIndicator("RSS Feed"/*,getResources().getDrawable(R.drawable.fragezeichenklein))*/).setContent(new Intent(this , Fragen.class )));
        //mTabHost.addTab(mTabHost.newTabSpec("third").setIndicator("Antworten"/*,getResources().getDrawable(R.drawable.ausrufezeicehn))*/).setContent(new Intent(this , Antworten.class )));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        mTabHost.setCurrentTab(Integer.parseInt(prefs.getString("home", "0")));


		web = (WebView) findViewById(R.id.aktualisieren);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);

		web = new WebView(this);
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://www.script-tutorials.com/demos/199/index.html");
		web.setWebViewClient(new myWebClient());
		web.setWebChromeClient(new WebChromeClient() {
			//The undocumented magic method override
			//Eclipse will swear at you if you try to put @Override here
			// For Android 3.0+
			public void openFileChooser(ValueCallback<Uri> uploadMsg) {

				mUploadMessage = uploadMsg;
				Intent i = new Intent(Intent.ACTION_GET_CONTENT);
				i.addCategory(Intent.CATEGORY_OPENABLE);
				i.setType("image/*");
				MainActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);

			}

			// For Android 3.0+
			public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
				mUploadMessage = uploadMsg;
				Intent i = new Intent(Intent.ACTION_GET_CONTENT);
				i.addCategory(Intent.CATEGORY_OPENABLE);
				i.setType("*/*");
				MainActivity.this.startActivityForResult(
						Intent.createChooser(i, "File Browser"),
						FILECHOOSER_RESULTCODE);
			}

			//For Android 4.1
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
				mUploadMessage = uploadMsg;
				Intent i = new Intent(Intent.ACTION_GET_CONTENT);
				i.addCategory(Intent.CATEGORY_OPENABLE);
				i.setType("image/*");
				MainActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), MainActivity.FILECHOOSER_RESULTCODE);

			}

		});


		//setContentView(web);


	}





	

	public void setURL(String link) {
		// TODO Auto-generated method stub
		url = link;
	}

	public String getURL() {
		// TODO Auto-generated method stub
		return url;
	}

}
