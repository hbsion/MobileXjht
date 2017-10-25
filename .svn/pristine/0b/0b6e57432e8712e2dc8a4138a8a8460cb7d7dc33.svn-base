package com.jky.xjht.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
	private static final String PREF_NAME = "appsys.local.dbfile";
	public static final String KEY_IS_LOGOUT = "is_logout";
	public static final String KEY_REGISTER_USER_IMAGE = "key_register_user_image";
	public static final String KEY_PRELIVE_IMAGE = "key_prelive_image";
	public static final String KEY_OPTION_IDS_STR = "key_option_ids_str";
	public static final String KEY_OPTION_ZLFX_LIST = "key_option_zlfx_list";
	public static final String KEY_OPTION_AQFX_LIST = "key_option_aqfx_list";
	public static final String KEY_OPTION_SJFX_LIST = "key_option_sjfx_list";
	
	public static final String KEY_ACCOUNT_TYPE_VALUE = "key_account_type_value";
	
	private static Preferences mPreferences;

	private static SharedPreferences mSettings;

	private Preferences(Context context) {
		mSettings = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	}

	public static Preferences getInstance(Context context) {
		if (mPreferences == null) {
			mPreferences = new Preferences(context.getApplicationContext());
		}
		return mPreferences;
	}

	public boolean isContains(String key) {
		return mSettings.contains(key);
	}

	public int getInt(String name, int def) {
		return mSettings.getInt(name, def);
	}

	public void putInt(String name, int value) {
		SharedPreferences.Editor edit = mSettings.edit();
		edit.putInt(name, value);
		edit.commit();
	}

	public String getString(String key) {
		return mSettings.getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		return mSettings.getString(key, defaultValue);
	}

	public void putString(String key, String value) {
		SharedPreferences.Editor edit = mSettings.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public long getLong(String key, long defaultValue) {
		return mSettings.getLong(key, defaultValue);
	}

	public void remove(String key) {
		SharedPreferences.Editor editor = mSettings.edit();
		editor.remove(key);
		editor.commit();
	}

	public static void clear() {
		SharedPreferences.Editor editor = mSettings.edit();
		editor.clear();
		editor.commit();
	}


}
