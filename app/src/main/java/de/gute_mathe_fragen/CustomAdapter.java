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

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	 
    private ArrayList _data;
    Context _c;
    
    CustomAdapter (ArrayList data, Context c){
        _data = data;
        _c = c;
    }
   
    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }
    
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
         View v = convertView;
         if (v == null)
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
         }
 
           LinearLayout linlay = (LinearLayout)v.findViewById(R.id.linlayout);
        
           TextView nameview = (TextView)v.findViewById(R.id.titel);
           TextView descview = (TextView)v.findViewById(R.id.decription);
           TextView timeView = (TextView)v.findViewById(R.id.time);
           TextView autorView = (TextView)v.findViewById(R.id.autor);
           TextView countView =(TextView)v.findViewById(R.id.count);
 
           Details msg = (Details) _data.get(position);
           nameview.setText(msg.name);
           descview.setText(msg.desc);
           timeView.setText(msg.time);    
           autorView.setText(msg.autor);
           countView.setText(msg.anzahlantworten);
           
           if(position % 2 == 0){
        	   linlay.setBackgroundColor(Color.parseColor("#FFFFFF"));
           }else{
        	   linlay.setBackgroundColor(Color.parseColor("#EAEAEA"));
           }             
        return v;
}
}
