package com.jinsungchoi.androidlibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by greenjin on 16. 5. 18.
 */
public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // this has this effect of pre-loading the ads in case user watch the rewarded ads
    // Even though for now it is only available in preference activity watching the ads
    // and not available in doing so in main activity
    // this loading in this context of main activity actually have this effect of caching
    // the ads video files in the same repository, I guess.
    // So in only case of wifi-abled state I pre-load it
    public static boolean isWifiAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getNetworkInfo(context);
        if(activeNetworkInfo==null)
            return false;

        int type = activeNetworkInfo.getType();

        return type == ConnectivityManager.TYPE_WIFI;
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo;
    }

}
