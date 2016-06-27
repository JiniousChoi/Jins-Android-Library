package com.jinsungchoi.androidlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by greenjin on 16. 2. 3.
 */
public class VersionUtils {

    private static final String LOG_TAG = VersionUtils.class.getSimpleName();

    public static void redirectToMarket(Activity activity) {
        final String appPackageName = activity.getPackageName();
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    static public String getMarketVersion(Context context) {
        String URL = "http://play.google.com/store/apps/details?id=" + context.getPackageName();
        Document doc;
        String version = null;

        try {
            doc = Jsoup.connect(URL).get();
            Element element = doc.select("[itemprop=softwareVersion]").first();
            version = element.text();
        } catch (IOException e) {
            String stackTrace = ExceptionUtils.getStackTrace(e);
            LogDev.e(LOG_TAG, "Compulsory Update error: " + stackTrace);
            // GoogleTracker.trackerHit(MyApplication.mTracker, "Compulsory Update", "Error: " + stackTrace, null, null);
            return null;
        }

        return version;
    }

    /**
     * major, minor 버전이 크면 필수업데이트며,
     * patch 버전은 선택업데이트로 스펙정의.
     *
     * @param localPackageVersion
     * @param marketPackageVersion
     * @return
     */
    public static boolean existCompulsoryUpdate(String localPackageVersion, String marketPackageVersion) {
        Version local = Version.parse(localPackageVersion);
        Version market = Version.parse(marketPackageVersion);

        if (market.major > local.major) {
            return true;
        }

        if (market.minor > local.minor) {
            return true;
        }

        return false;
    }

    static public class Version implements Comparable<Version> {
        //기본적으로 0이 셋팅되면 비교대조가 편리해진다.
        Integer major = 0;
        Integer minor = 0;
        Integer patch = 0;

        static public Version parse(String version) {

            Version v = new Version();
            String[] versions = version.split("\\.");

            int length = versions.length;

            switch (length) {
                case 3: {
                    v.patch = Integer.parseInt(versions[2]);
                }
                case 2: {
                    v.minor = Integer.parseInt(versions[1]);
                    v.major = Integer.parseInt(versions[0]);
                    break;
                }
                default: {
                    String errorMsg = String.format("version(%s) caused error while being parsed", version);
                    if (BuildConfig.DEBUG) {
                        throw new InvalidParameterException(errorMsg);
                    }
                }
            }

            return v;
        }

        @Override
        public boolean equals(Object o) {
            return this.major != ((Version) o).major ? false
                    : this.minor != ((Version) o).minor ? false
                    : this.patch != ((Version) o).patch ? false
                    : true;
        }


        @Override
        public int compareTo(Version another) {
            if (this.major > another.major) {
                return 1;
            } else if (this.major < another.major) {
                return -1;
            }

            if (this.minor > another.minor) {
                return 1;
            } else if (this.minor < another.minor) {
                return -1;
            }

            if (this.patch > another.patch) {
                return 1;
            } else if (this.patch < another.patch) {
                return -1;
            }

            return 0;

        }
    }
}
