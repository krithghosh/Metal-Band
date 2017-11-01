package com.kritartha.blacklanechallenge.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kritarthaghosh on 01/11/17.
 */
@Module
public class AppSchedulerModule {

    @Provides
    @Singleton
    AppScheduler providesAppScheduler() {
        return new AppScheduler();
    }
}
