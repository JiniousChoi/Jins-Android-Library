package com.jinsungchoi.androidlibrary;

/*
Copyright (C) 2012 Sveinung Kval Bakken, sveinung.bakken@gmail.com
Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:
The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jinsungchoi.androidlibrary.cipher.ICipherUtils;

public class SecurePreferences {

    private static final String LOG_TAG = SecurePreferences.class.getSimpleName();

    private final boolean encryptKeys;
    private final SharedPreferences preferences;
    private /*todo final */ICipherUtils cipherUtils;

    /**
     * This will initialize an instance of the SecurePreferences class
     * @param cipherUtils
     * @param context your current context.
     * @param preferenceName name of preferences file (preferenceName.xml), default shared preference if empty
     * @param encryptKeys settings this to false will only encrypt the values,
     */
    public SecurePreferences(ICipherUtils cipherUtils, Context context, String preferenceName, boolean encryptKeys) {
        this.cipherUtils = cipherUtils;

        if(StringUtils.isEmpty(preferenceName)) {
            this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            this.preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        }

        this.encryptKeys = encryptKeys;
    }

    public void put(String key, String value) {
        if (value == null) {
            preferences.edit().remove(toKey(key)).apply();
        }
        else {
            putValue(toKey(key), value);
        }
    }

    /**
     * Wrapped by jin
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        put(key, String.valueOf(value));
    }

    public boolean containsKey(String key) {
        return preferences.contains(toKey(key));
    }

    public void removeValue(String key) {
        preferences.edit().remove(toKey(key)).apply();
    }

    public String getString(String key) {
        if (preferences.contains(toKey(key))) {
            //todo: question: ""를 decrypt하면 ""인가????? 그냥 궁금함. 다른 영향은 없음.
            String securedEncodedValue = preferences.getString(toKey(key), "");
            String decrypted = cipherUtils.decrypt(securedEncodedValue);

            LogDev.d(LOG_TAG, "securedEncodedValue -- " + securedEncodedValue);
            LogDev.d(LOG_TAG, "decrypted -- " + decrypted);

            return decrypted;
        }
        return null;
    }

    /**
     * Wrapped by jin
     *
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int defValue) {
        String s = getString(key);
        if(StringUtils.isEmpty(s)) {
            return defValue;
        }

        return Integer.valueOf(s);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    private String toKey(String key) {
        if (encryptKeys)
            return cipherUtils.encrypt(key);
        else return key;
    }

    private void putValue(String key, String value) {
        String secureValueEncoded = cipherUtils.encrypt(value);

        preferences.edit().putString(key, secureValueEncoded).apply();
    }

}
