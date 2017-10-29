package com.kritartha.blacklanechallenge.database;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * Created by kritarthaghosh on 29/10/17.
 */
@Module
public class DbModule {
    private static final String TAG = "DbModule";

    @Provides
    @Singleton
    public SQLiteOpenHelper provideOpenHelper(Application application) {
        return new DatabaseHelper(application);
    }

    @Provides
    @Singleton
    public SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder().logger(new SqlBrite.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, "provideSqlBrite");
            }
        }).build();
    }

    @Provides
    @Singleton
    public BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        return sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    }
}
