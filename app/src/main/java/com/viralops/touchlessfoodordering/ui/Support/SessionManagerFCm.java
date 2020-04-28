package com.viralops.touchlessfoodordering.ui.Support;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagerFCm {
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Vserve_IRDFCM";


    // User name (make variable public to access from outside)
    private static final String KEY_ACCESSTOEKN = "accesstoken";


    public static String getKeyAccesstoekn() {
        return KEY_ACCESSTOEKN;
    }

    public  void setKeyAccesstoekn() {

    }



    // Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManagerFCm(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



}
