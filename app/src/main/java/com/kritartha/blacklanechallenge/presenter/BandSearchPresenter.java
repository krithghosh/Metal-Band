package com.kritartha.blacklanechallenge.presenter;

import android.util.Log;

import com.kritartha.blacklanechallenge.utils.AppScheduler;
import com.kritartha.blacklanechallenge.utils.IScheduler;
import com.kritartha.blacklanechallenge.view.BandSearchContract;
import com.kritartha.blacklanechallenge.model.bandSearch.BandData;
import com.kritartha.blacklanechallenge.model.bandSearch.BandSearchResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class BandSearchPresenter implements BandSearchContract.Presenter {
    private static final String TAG = "BandSearchPresenter";

    private AppScheduler mIScheduler;
    private DataRepository mDataRepository;
    private BandSearchContract.View mView;
    private Subscription mBandSearchSubscription;
    private Subscription mBandHistorySubscription;

    @Inject
    public BandSearchPresenter(DataRepository mDataRepository, AppScheduler mIScheduler) {
        this.mDataRepository = mDataRepository;
        this.mIScheduler = mIScheduler;
    }

    @Override
    public void setView(BandSearchContract.View mView) {
        this.mView = mView;
    }

    public void unSubscribeBandSearch() {
        if (!(mBandSearchSubscription == null || mBandSearchSubscription.isUnsubscribed())) {
            mBandSearchSubscription.unsubscribe();
        }
    }

    @Override
    public void getBandSearch(String keyword) {
        mBandSearchSubscription = mDataRepository.getBandSearch(keyword)
                .subscribeOn(mIScheduler.backgroundThread())
                .observeOn(mIScheduler.mainThread())
                .subscribe(new Subscriber<BandSearchResponse>() {
                    BandSearchResponse response = null;

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: getBandSearch");
                        if (response == null || response.getCode() != 200) {
                            mView.showError("No bands found with " + keyword);
                            return;
                        }
                        BandData bandData = response.getData();
                        if (bandData == null || bandData.getSearchResults() == null
                                || bandData.getSearchResults().size() == 0) {
                            mView.showError("No bands found with " + keyword);
                            return;
                        }
                        List<String> list = new ArrayList<>();
                        for (SearchResult item : bandData.getSearchResults()) {
                            list.add(item.getName());
                        }
                        mView.updateSearchList(bandData.getSearchResults(), list);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError: getBandSearch", throwable);
                        mView.showError("Could not fetch data");
                    }

                    @Override
                    public void onNext(BandSearchResponse response) {
                        Log.d(TAG, "onNext: getBandSearch");
                        this.response = response;
                    }
                });
    }

    @Override
    public void unSubscribeBandHistory() {
        if (!(mBandHistorySubscription == null || mBandHistorySubscription.isUnsubscribed())) {
            mBandHistorySubscription.unsubscribe();
        }
    }

    @Override
    public void getBandHistory() {
        mBandHistorySubscription = mDataRepository.getBandMetadata()
                .subscribeOn(mIScheduler.backgroundThread())
                .observeOn(mIScheduler.mainThread())
                .subscribe(new Subscriber<List<SearchResult>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: getBandHistory");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError: getBandHistory", throwable);
                    }

                    @Override
                    public void onNext(List<SearchResult> searchResults) {
                        Log.d(TAG, "onNext: getBandHistory");
                        mView.updateSearchHistoryList(searchResults);
                    }
                });
    }
}
