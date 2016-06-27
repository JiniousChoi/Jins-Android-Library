package com.jinsungchoi.androidlibrary.cipher;

/**
 * Created by greenjin on 16. 6. 4.
 */
public interface ICipherUtils {
    String encrypt(String value);

    String decrypt(String securedEncodedValue);

}
