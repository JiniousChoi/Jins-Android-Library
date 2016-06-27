package com.jinsungchoi.androidlibrary;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by greenjin on 16. 6. 10.
 */
public class SQLiteUtils {

    /**
     * Guarantees that SQLiteOpenHelper, SQLiteDatabase are closed when exit this method
     * @param callback
     */
    public static void withContextWritable(SQLiteOpenHelper openHelper, Callback callback) {
        WithContext withContext = new WithContextWritable();
        withContext.exec(openHelper, callback);
    }

    public static void withContextReadable(SQLiteOpenHelper openHelper, Callback callback) {
        WithContext withContext = new WithContextReadadle();
        withContext.exec(openHelper, callback);
    }

    /**
     * define business logic with the given db reference
     */
    public interface Callback {
        void doLogic(SQLiteDatabase db);
    }

    static abstract class WithContext {

        abstract SQLiteDatabase setDatabase(SQLiteOpenHelper sqLiteOpenHelper);

        void exec(SQLiteOpenHelper openHelper, Callback callback) {
                SQLiteDatabase db = setDatabase(openHelper);

                callback.doLogic(db);

                db.close();
                openHelper.close();
        }
    }

    static class WithContextReadadle extends WithContext {

        @Override
        SQLiteDatabase setDatabase(SQLiteOpenHelper sqLiteOpenHelper) {
            return sqLiteOpenHelper.getReadableDatabase();
        }
    }

    static class WithContextWritable extends WithContext {
        @Override
        SQLiteDatabase setDatabase(SQLiteOpenHelper sqLiteOpenHelper) {
            return sqLiteOpenHelper.getWritableDatabase();
        }
    }
}
