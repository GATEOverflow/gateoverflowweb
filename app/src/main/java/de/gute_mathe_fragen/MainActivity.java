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
package de.gute_mathe_fragen;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
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
