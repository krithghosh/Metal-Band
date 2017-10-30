package com.kritartha.blacklanechallenge;

import android.app.Application;

import com.kritartha.blacklanechallenge.component.ApplicationComponent;
import com.kritartha.blacklanechallenge.component.DaggerApplicationComponent;
import com.kritartha.blacklanechallenge.module.ApplicationModule;
import com.kritartha.blacklanechallenge.network.NetworkModule;
import com.kritartha.blacklanechallenge.repository.RepositoryModule;

/**
 * Created by kritarthaghosh on 26/10/17.
 */

public class MetalBandApplication extends Application {
    protected static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
