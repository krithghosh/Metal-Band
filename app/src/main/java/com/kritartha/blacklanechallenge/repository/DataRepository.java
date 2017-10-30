package com.kritartha.blacklanechallenge.repository;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.BandSearchResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class DataRepository {

    private final LocalDataRepository localDataRepository;
    private final RemoteDataRepository remoteDataRepository;

    @Inject
    public DataRepository(LocalDataRepository localDataRepository,
                          RemoteDataRepository remoteDataRepository) {
        this.localDataRepository = localDataRepository;
        this.remoteDataRepository = remoteDataRepository;
    }

    public Observable<List<SearchResult>> getBandMetadata() {
        return localDataRepository.getBandMetadata();
    }

    public void storeBandMetadata(SearchResult searchResult) {
        localDataRepository.insertBandMetadata(searchResult);
    }

    public void deleteBandMetadata() {
        localDataRepository.deleteBandMetadata();
    }

    public Observable<BandSearchResponse> getBandSearch(String keyword) {
        return remoteDataRepository.getBandSearch(keyword);
    }

    public Observable<BandDetailResponse> getBandDetail(String bandId) {
        return remoteDataRepository.getBandDetail(bandId);
    }
}
