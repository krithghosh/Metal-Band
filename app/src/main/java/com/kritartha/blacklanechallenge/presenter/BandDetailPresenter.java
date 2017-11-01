package com.kritartha.blacklanechallenge.presenter;

import android.util.Log;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.repository.DataRepository;
import com.kritartha.blacklanechallenge.utils.AppScheduler;
import com.kritartha.blacklanechallenge.utils.IScheduler;
import com.kritartha.blacklanechallenge.view.BandDetailContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class BandDetailPresenter implements BandDetailContract.Presenter {
    private static final String TAG = "BandDetailPresenter";

    private AppScheduler mIScheduler;
    private BandDetailContract.View mView;
    private DataRepository mDataRepository;
    private Subscription mBandDetailSubscription;

    @Inject
    public BandDetailPresenter(DataRepository mDataRepository, AppScheduler mIScheduler) {
        this.mDataRepository = mDataRepository;
        this.mIScheduler = mIScheduler;
    }

    @Override
    public void setView(BandDetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void storeBandSearch(SearchResult searchResult) {
        mDataRepository.storeBandMetadata(searchResult);
    }

    @Override
    public void unSubscribeBandDetail() {
        if (!(mBandDetailSubscription == null || mBandDetailSubscription.isUnsubscribed())) {
            mBandDetailSubscription.unsubscribe();
        }
    }

    @Override
    public void getBandDetail(String id) {
        mBandDetailSubscription = mDataRepository.getBandDetail(id)
                .subscribeOn(mIScheduler.backgroundThread())
                .observeOn(mIScheduler.mainThread())
                .subscribe(new Subscriber<BandDetailResponse>() {
                    BandDetailResponse bandDetailResponse = null;

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: getBandDetail");
                        mView.updateBandDetail(bandDetailResponse);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError: getBandDetail", throwable);
                        mView.showError("Could not fetch band detail");
                    }

                    @Override
                    public void onNext(BandDetailResponse bandDetailResponse) {
                        Log.d(TAG, "onNext: getBandDetail");
                        this.bandDetailResponse = bandDetailResponse;
                    }
                });
    }
}
