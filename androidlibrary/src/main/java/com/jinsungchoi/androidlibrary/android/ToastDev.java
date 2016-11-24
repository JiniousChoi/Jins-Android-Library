package com.jinsungchoi.androidlibrary.android;

import android.content.Context;
import android.widget.Toast;

import com.jinsungchoi.androidlibrary.DebugUtils;


/**
 * Created by greenjin on 16. 5. 17.
 */
public class ToastDev {

    public static void makeTextAndShow(Context context, CharSequence text, int duration) {
        if(!DebugUtils.isDebuggable()) {
            return;
        }

        Toast toast = Toast.makeText(context, String.format("[ToastDev]%s", text), duration);
        toast.show();
    }
}
