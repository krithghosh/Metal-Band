package com.kritartha.blacklanechallenge.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.repository.DataRepository;
import com.kritartha.blacklanechallenge.view.BandDetailContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kritarthaghosh on 01/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class BandDetailPresenterTest {

    @Mock
    private DataRepository mDataRepository;

    @Mock
    private BandDetailContract.View mBandDetailView;

    private BandDetailPresenter mBandDetailPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mBandDetailPresenter = new BandDetailPresenter(mDataRepository);
    }

    /*@Test
    public void testStoreBandMetadata() {
        SearchResult searchResult = Mockito.mock(SearchResult.class);
        mBandDetailPresenter = new BandDetailPresenter(mDataRepository);
        mBandDetailPresenter.setView(mBandDetailView);

        mBandDetailPresenter.storeBandSearch(searchResult);
        verify(mDataRepository).storeBandMetadata(searchResult);
    }*/

    @Test
    public void testGetBandDetail() {
        BandDetailResponse bandDetailResponse = Mockito.mock(BandDetailResponse.class);
        when(mDataRepository.getBandDetail(anyString()))
                .thenReturn(Observable.create(subscriber -> {
                    subscriber.onNext(bandDetailResponse);
                    subscriber.onCompleted();
                }));

        mBandDetailPresenter.setView(mBandDetailView);
        mBandDetailPresenter.getBandDetail(anyString());

        verify(mDataRepository).getBandDetail(anyString());
        verify(mBandDetailView).updateBandDetail(bandDetailResponse);
    }
}