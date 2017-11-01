package com.kritartha.blacklanechallenge.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetails;
import com.kritartha.blacklanechallenge.model.bandSearch.BandData;
import com.kritartha.blacklanechallenge.model.bandSearch.BandSearchResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.repository.DataRepository;
import com.kritartha.blacklanechallenge.utils.AppScheduler;
import com.kritartha.blacklanechallenge.view.BandSearchContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kritarthaghosh on 01/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class BandSearchPresenterTest {

    @Mock
    private DataRepository mDataRepository;

    @Mock
    private BandSearchContract.View mBandSearchView;

    private BandSearchPresenter mBandSearchPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mBandSearchPresenter = new BandSearchPresenter(mDataRepository, new AppScheduler());
    }

    @Test
    public void testGetBandSearchHistory() {
        SearchResult searchResult = Mockito.mock(SearchResult.class);
        List<SearchResult> searchResultList = new ArrayList<>();
        searchResultList.add(searchResult);
        mBandSearchPresenter.setView(mBandSearchView);

        when(mDataRepository.getBandMetadata())
                .thenReturn(Observable.create(subscriber -> {
                    subscriber.onNext(searchResultList);
                    subscriber.onCompleted();
                }));

        mBandSearchPresenter.getBandHistory();
        verify(mDataRepository).getBandMetadata();
    }

    @Test
    public void testGetBandSearch() {
        BandSearchResponse bandSearchResponse = Mockito.mock(BandSearchResponse.class);
        List<SearchResult> searchResultList = new ArrayList<>();
        BandData bandData = new BandData();
        bandData.setSearchResults(searchResultList);
        bandSearchResponse.setData(bandData);
        when(mDataRepository.getBandSearch(anyString()))
                .thenReturn(Observable.create(subscriber -> {
                    subscriber.onNext(bandSearchResponse);
                    subscriber.onCompleted();
                }));

        mBandSearchPresenter.setView(mBandSearchView);
        mBandSearchPresenter.getBandSearch(anyString());

        verify(mDataRepository).getBandSearch(anyString());
    }
}