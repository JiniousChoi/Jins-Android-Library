//package com.jinsungchoi.mylibrary.util.android;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.ResolveInfo;
//import android.os.Parcelable;
//
//import com.jinsungchoi.android.immersionrevolution.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by greenjin on 16. 6. 18.
// */
//public class ShareBySpecificApps {
//
//    public static void share(Activity activity, String plainUrlForSharing) {
//        List<Intent> targetedShareIntents = new ArrayList<>();
//        String shareTitle = activity.getString(R.string.text_share_title);
//        String shareMessage = activity.getString(R.string.text_share_message, plainUrlForSharing);
//
//        String[] targetAppPackageNames = {
//                "com.facebook.katana",
//                "com.facebook.orca",
//                "com.facebook.lite",
//                "com.twitter.android",
//                "com.kakao.talk",
//                "jp.naver.line.android",
//                "com.whatsapp",
//        };
//        for (String targetAppPackageName : targetAppPackageNames) {
//            Intent targetAppIntent = getShareIntent(activity, targetAppPackageName, shareTitle, shareMessage);
//            if (targetAppIntent != null)
//                targetedShareIntents.add(targetAppIntent);
//        }
//
//        Intent chooser = Intent.createChooser(targetedShareIntents.remove(0), activity.getString(R.string.text_share_sharer_title));
//
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
//
//        activity.startActivity(chooser);
//    }
//
//    private static Intent getShareIntent(Activity activity, String type, String subject, String text)
//    {
//        boolean found = false;
//        Intent share = new Intent(android.content.Intent.ACTION_SEND);
//        share.setType("text/plain");
//
//        // gets the list of intents that can be loaded.
//        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(share, 0);
////        LogDev.i(LOG_TAG, "resinfo: " + resInfo);
//        if (!resInfo.isEmpty()){
//            for (ResolveInfo info : resInfo) {
//                if (info.activityInfo.packageName.toLowerCase().equals(type) ||
//                        info.activityInfo.name.toLowerCase().equals(type) ) {
//                    share.putExtra(Intent.EXTRA_SUBJECT,  subject);
//                    share.putExtra(Intent.EXTRA_TEXT,     text);
//                    share.setPackage(info.activityInfo.packageName);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found)
//                return null;
//
//            return share;
//        }
//        return null;
//    }
//
//}
