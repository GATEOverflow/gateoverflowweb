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

public class Details {
	 
     String name;
     String desc;
     String time;
     String autor;
     String anzahlantworten;

    

	public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
     public String getDesc() {
         return desc;
     }

     public void setDesc(String desc) {
         this.desc = desc;
     }
 
     
     public String getTime() {
         return time;
     }

     public void setTime(String time) {
         this.time = time;
     }
    
     public String getAutor() {
         return autor;
     }

     public void setAutor(String autor) {
         this.autor = autor;
     }
     public String getAnzahlAntworten() {
         return anzahlantworten;
     }

     public void setAnzahlAntworten(String anzahlantworten) {
         this.anzahlantworten = anzahlantworten;
     }
     
}
