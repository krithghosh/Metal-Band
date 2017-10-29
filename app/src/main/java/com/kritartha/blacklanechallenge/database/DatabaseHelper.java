package com.kritartha.blacklanechallenge.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kritartha.blacklanechallenge.utils.Constants;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = Constants.DB_NAME;
    private static final int DB_VERSION = 1;
    public static SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.i(TAG, "DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate");
        try {
            sqLiteDatabase.execSQL(BandMetadataTable.CREATE_TABLE);
            DatabaseHelper.sqLiteDatabase = sqLiteDatabase;
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        super.close();
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }
}
