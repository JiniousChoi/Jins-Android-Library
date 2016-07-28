package com.jinsungchoi.androidlibrary.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.jinsungchoi.androidlibrary.LogDev;
import com.jinsungchoi.androidlibrary.R;
import com.jinsungchoi.androidlibrary.StringUtils;

/**
 * Support permission request on Marshmallow and above
 *
 * Created by greenjin on 16. 7. 21.
 */
public class PermissionManager {

    private static final String LOG_TAG = PermissionManager.class.getSimpleName();

    public static boolean isPermissionGranted(final DataForPermissionRequest data) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (data.activity.checkSelfPermission(data.permission)
                    == PackageManager.PERMISSION_GRANTED) {
                LogDev.v(LOG_TAG,"Permission is granted");
                return true;

            } else {
                LogDev.v(LOG_TAG,"Permission is revoked");
                return false;
            }

        } else { //permission is automatically granted on sdk<23 upon installation
            LogDev.v(LOG_TAG,"Permission is granted");
            return true;
        }

    }

    public static void requestPermission(final DataForPermissionRequest data) {
        new AlertDialog.Builder(data.activity)
                .setTitle(data.title)
                .setMessage(data.message)
                .setCancelable(false)
                .setPositiveButton(R.string.alert_button_confirm, new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(data.activity, data.permissions, data.requestCode);
                }
            }).create()
                .show();
    }

    public static class DataForPermissionRequest {
        Activity activity;
        String title;
        String message;
        String permission;
        String[] permissions; // new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}
        Integer requestCode;

        public DataForPermissionRequest() {

        }

        public boolean isValid() {
            return activity!=null
                    && StringUtils.isEmpty(title)
                    && StringUtils.isEmpty(message)
                    && StringUtils.isEmpty(permission)
                    && requestCode!=null;
        }

        public DataForPermissionRequest setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public DataForPermissionRequest setTitle(String title) {
            this.title = title;
            return this;
        }

        public DataForPermissionRequest setMessage(String message) {
            this.message = message;
            return this;
        }

        public DataForPermissionRequest setPermission(String permission) {
            this.permission = permission;
            setPermissions(permission);
            return this;
        }

        private DataForPermissionRequest setPermissions(String permission) {
            this.permissions = new String[]{permission};
            return this;
        }

        public DataForPermissionRequest setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }
    }

}
