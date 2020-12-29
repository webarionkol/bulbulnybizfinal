package com.apps.nybizz.Network;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {
    static String PREF_FILE = "nbizzclone";
    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String getSharedPreferenceString(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(key, "");
    }


}
