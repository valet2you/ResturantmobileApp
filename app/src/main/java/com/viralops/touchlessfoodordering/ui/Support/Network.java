package com.viralops.touchlessfoodordering.ui.Support;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
    Context context;

    public Network(Context context) {
        this.context=context;

    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo i = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean mobileConnected = i.getState() == NetworkInfo.State.CONNECTED;

        boolean isConnected = i != null &&
                i.isConnected();
        if (i == null)
            return false;
        if(i.getState()== NetworkInfo.State.CONNECTED){
            return true;
        }
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;


        return true;
    }
    public static boolean isNetworkAvailable2(Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }
}
