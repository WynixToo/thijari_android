package com.example.android.thijari.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefStorage {

	private SharedPreferences sharedPreferences;
	private String prefName;
	private String prefData;

	// 1 is username, user password, last login date, channel id

	public PrefStorage(Context context, String sessionName) {
		sharedPreferences = context.getSharedPreferences(sessionName,
				context.MODE_PRIVATE);

	}

	public String getData(String name, String defaultValue) {
		return sharedPreferences.getString(name, defaultValue);
	}

	// public String getItem(String name, int position) {
	// return getData(name) != "" ? getData(name).split(";")[position] : "";
	// }

	public void deletePreference() {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}

	public void savePreference(String name, String defaultValue, String data) {
		if (!getData(name, defaultValue).equals(""))
			deletePreference();

		prefName = name;
		prefData = data;
		SharedPreferences.Editor prefEditor = sharedPreferences.edit();
		prefEditor.putString(prefName, prefData);
		prefEditor.commit();
	}

}
