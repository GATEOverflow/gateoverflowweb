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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;

public class Servicestarter extends BroadcastReceiver {
	
	private static final String DEBUG_TAG = "gmf-debug";
/* Hier werden die beiden Hintergrundservice gestartet.
*/
	
	@Override
    public void onReceive(Context context, Intent intent) {
		 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		 int time = Integer.parseInt(prefs.getString("peridodendauer", "10"));
		 AlarmManager am = (AlarmManager) context
                 .getSystemService(Context.ALARM_SERVICE);
			Intent antwortintent = new Intent(context,
					ServiceAntworten.class);
			PendingIntent antwortenpendingintent = PendingIntent.getService(
					context, 0, antwortintent, 0);
			long intervalantworten = DateUtils.MINUTE_IN_MILLIS * time;
		 	
			Intent fragenintent = new Intent(context,
					ServiceFragen.class);
			PendingIntent fragenpendingintent = PendingIntent.getService(
					context, 0, fragenintent, 0);
			AlarmManager am2 = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			long intervalfragen = DateUtils.MINUTE_IN_MILLIS * time;
		 
		 
      if ((!prefs.getBoolean("running", false)) || (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))) {
            Log.d(DEBUG_TAG, " Bradcast Receiver gestartet.");
           
            Editor ed = prefs.edit();
            ed.putBoolean("running", true);
            ed.commit();
            if (prefs.getBoolean("antwortcheck", false)) {
				long firstStart = System.currentTimeMillis() + 2000;
				
				am.setInexactRepeating(AlarmManager.RTC, firstStart,
						intervalantworten, antwortenpendingintent);
				Log.d(DEBUG_TAG, "Antwortservice wird gestartet");
			}
           
			if (prefs.getBoolean("fragencheck", false)) {
				
				long firstStart2 = System.currentTimeMillis() + 5000;			
				am2.setInexactRepeating(AlarmManager.RTC,
						firstStart2, intervalfragen, fragenpendingintent);
				Log.d(DEBUG_TAG, "Fragenservice wird gestartet");
				}
      			}else{
				am.cancel(antwortenpendingintent);
				am2.cancel(fragenpendingintent);
				
				if (prefs.getBoolean("antwortcheck", false)) {
					long firstStart = System.currentTimeMillis() + 2000;
					
					am.setInexactRepeating(AlarmManager.RTC, firstStart,
							intervalantworten, antwortenpendingintent);
					Log.d(DEBUG_TAG, "Antwortservice wird gestartet");
				}
	           
				if (prefs.getBoolean("fragencheck", false)) {
					
					long firstStart2 = System.currentTimeMillis() + 5000;			
					am2.setInexactRepeating(AlarmManager.RTC,
							firstStart2, intervalfragen, fragenpendingintent);
					Log.d(DEBUG_TAG, "Fragenservice wird gestartet");
			}
			
            
        }
	}
}
