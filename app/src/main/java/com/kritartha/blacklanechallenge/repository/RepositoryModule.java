package com.kritartha.blacklanechallenge.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kritarthaghosh on 29/10/17.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    LocalDataRepository providesLocalDataRepository() {
        return new LocalDataRepository();
    }

    @Provides
    @Singleton
    RemoteDataRepository providesRemoteDataRepository() {
        return new RemoteDataRepository();
    }
}
