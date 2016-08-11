package com.jinsungchoi.androidlibrary.android;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.jinsungchoi.androidlibrary.DebugUtils;
import com.jinsungchoi.androidlibrary.StringUtils;

import java.util.Map;

/**
 * Created by greenjin on 16. 6. 4.
 */
public class GoogleTracker {

    public static void trackerHit(Tracker tracker, String category, String action, String label, Long value) {
//        if(TestUtils.skipTracking()) {
//            return;
//        }

        String PREFIX = "";
        if(DebugUtils.isDebuggable()) {
            PREFIX = "[DEBUG]";
        }

        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();

        if(StringUtils.isNotEmpty(category))
            eventBuilder.setCategory(PREFIX + category);

        if(StringUtils.isNotEmpty(action))
            eventBuilder.setAction(PREFIX + action);

        if(StringUtils.isNotEmpty(label))
            eventBuilder.setLabel(PREFIX + label);

        if(value != null)
            eventBuilder.setValue(value);

        Map<String, String> hit = eventBuilder.build();

        tracker.send(hit);
    }

    public static void trackerScreenView(Tracker tracker, String screenName) {
//        if(TestUtils.skipTracking()) {
//            return;
//        }

        String PREFIX = "";
        if(DebugUtils.isDebuggable()) {
            PREFIX = "[DEBUG]";
        }

        tracker.setScreenName(PREFIX + screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
