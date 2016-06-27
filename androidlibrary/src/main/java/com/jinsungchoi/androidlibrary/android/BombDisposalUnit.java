//package com.jinsungchoi.mylibrary.util.android;
//
//import android.app.Activity;
//
//import com.jinsungchoi.android.immersionrevolution.MyApplication;
//import com.jinsungchoi.android.immersionrevolution.purchase.ads.MyRewardedVideoAdsManager;
//import com.jinsungchoi.android.immersionrevolution.util.LogDev;
//
///**
// * Created by greenjin on 16. 6. 3.
// */
//public class BombDisposalUnit {
//
//    private static final String LOG_TAG = BombDisposalUnit.class.getSimpleName();
//    private static final String category = "Error";
//    private static final String action = "Rewarded Ads: ";
//
//    /**
//     * 보고된 버그 우회책
//     * 확실하진 않지만 업데이트된 google-play-service 9.0.0 과 연관이 있는듯.
//     * 일단 초기화와 로드하는 과정에서 일어나는 런타임 예외이므로, 여기에서 처리하도록 함.
//     *
//     * @param activity
//     * @return null if runtime exception occurred.
//     */
//    public static MyRewardedVideoAdsManager initRewardedAd(Activity activity) {
//        MyRewardedVideoAdsManager rewardedAdsManager = null;
//
//        try {
//            rewardedAdsManager = new MyRewardedVideoAdsManager(activity);
//
//        } catch (RuntimeException re) {
//            LogDev.e(LOG_TAG, re.getMessage());
//            GoogleTracker.trackerHit(MyApplication.mTracker, category, action + re.getMessage(), null, null);
//        }
//
//        return rewardedAdsManager;
//    }
//
//    /**
//     *
//     * @param rewardedAdsManager null safe
//     * @return
//     */
//    public static boolean loadRewardedAd(MyRewardedVideoAdsManager rewardedAdsManager) {
//
//        if(rewardedAdsManager==null) {
//            return false;
//        }
//
//        boolean succeed = false;
//
//        try {
//            rewardedAdsManager.loadRewardedVideoAd();
//            succeed = true;
//
//        } catch (RuntimeException re) {
//            LogDev.e(LOG_TAG, re.getMessage());
//            GoogleTracker.trackerHit(MyApplication.mTracker, category, action + re.getMessage(), null, null);
//        }
//
//        return succeed;
//    }
//
//}
