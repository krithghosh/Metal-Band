package com.kritartha.blacklanechallenge.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kritartha.blacklanechallenge.database.DbModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kritarthaghosh on 29/10/17.
 */
@Module(includes = {DbModule.class})
public class ApplicationModule {

    private final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
