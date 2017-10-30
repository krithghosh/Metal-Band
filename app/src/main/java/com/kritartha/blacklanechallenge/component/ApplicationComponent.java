package com.kritartha.blacklanechallenge.component;

import com.kritartha.blacklanechallenge.view.activity.BandActivity;
import com.kritartha.blacklanechallenge.view.fragment.BandDetailFragment;
import com.kritartha.blacklanechallenge.view.fragment.BandSearchFragment;
import com.kritartha.blacklanechallenge.module.ApplicationModule;
import com.kritartha.blacklanechallenge.repository.DataRepository;
import com.kritartha.blacklanechallenge.repository.LocalDataRepository;
import com.kritartha.blacklanechallenge.repository.RemoteDataRepository;
import com.kritartha.blacklanechallenge.repository.RepositoryModule;
import com.kritartha.blacklanechallenge.network.NetworkModule;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by kritarthaghosh on 29/10/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {

    Retrofit retrofit();

    BriteDatabase briteDatabase();

    DataRepository dataRepository();

    void inject(LocalDataRepository localDataRepository);

    void inject(RemoteDataRepository remoteDataRepository);

    void inject(BandActivity bandActivity);

    void inject(BandSearchFragment bandSearchFragment);

    void inject(BandDetailFragment bandDetailFragment);
}