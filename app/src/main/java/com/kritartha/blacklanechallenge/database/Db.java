package com.kritartha.blacklanechallenge.database;

import android.database.Cursor;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public final class Db {
    private static final int BOOLEAN_FALSE = 0;
    private static final int BOOLEAN_TRUE = 1;

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static boolean getBoolean(Cursor cursor, String columnName) {
        return getInt(cursor, columnName) == BOOLEAN_TRUE;
    }

    public static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    private Db() {
        throw new AssertionError("No instances.");
    }
}