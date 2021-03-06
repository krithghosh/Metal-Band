package com.kritartha.blacklanechallenge.repository;

import com.kritartha.blacklanechallenge.BuildConfig;
import com.kritartha.blacklanechallenge.MetalBandApplication;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.BandSearchResponse;
import com.kritartha.blacklanechallenge.network.RestApiService;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class RemoteDataRepository {
    private static final String TAG = "RemoteDataRepository";

    @Inject
    Retrofit retrofit;

    public RemoteDataRepository() {
        MetalBandApplication.getAppComponent().inject(this);
    }

    public Observable<BandSearchResponse> getBandSearch(String keyword) {
        return retrofit.create(RestApiService.class)
                .getBandSearch(keyword, BuildConfig.API_KEY);
    }

    public Observable<BandDetailResponse> getBandDetail(String bandId) {
        return retrofit.create(RestApiService.class)
                .getBandDetails(bandId, BuildConfig.API_KEY);
    }
}