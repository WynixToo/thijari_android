package com.example.android.thijari.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilMethod {

	public static boolean isConnection(Context context) {
		ConnectivityManager connection = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = connection.getActiveNetworkInfo();
		if (i == null || !i.isConnected() || !i.isAvailable()) {
			return false;
		}
		return true;
	}
	
	public static String CapsFirst(String str) {
	    String[] words = str.split(" ");
	    StringBuilder ret = new StringBuilder();
	    for(int i = 0; i < words.length; i++) {
	        ret.append(Character.toUpperCase(words[i].charAt(0)));
	        ret.append(words[i].substring(1));
	        if(i < words.length - 1) {
	            ret.append(' ');
	        }
	    }
	    return ret.toString();
	}
}
