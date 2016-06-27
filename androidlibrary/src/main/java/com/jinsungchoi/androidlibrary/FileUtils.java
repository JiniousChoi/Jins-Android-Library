package com.jinsungchoi.androidlibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by greenjin on 16. 6. 4.
 */
public class FileUtils {

    public static Exception write(String path, String content) {
        Writer writer;
        Exception ex = null;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(path), "utf-8"));
            writer.write(content);
            writer.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ex = e;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ex = e;
        } catch (IOException e) {
            e.printStackTrace();
            ex = e;
        } catch (RuntimeException e) {
            ex = e;
        }

        return ex;
    }


    public static String readOneline(File file) {
        String oneline = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            oneline = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oneline;
    }
}
