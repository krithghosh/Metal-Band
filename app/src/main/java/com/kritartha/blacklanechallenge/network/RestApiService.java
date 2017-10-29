package com.kritartha.blacklanechallenge.network;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.BandSearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static com.kritartha.blacklanechallenge.utils.Constants.BAND_DETAIL_URL;
import static com.kritartha.blacklanechallenge.utils.Constants.BAND_SEARCH_URL;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public interface RestApiService {

    @GET(BAND_SEARCH_URL)
    Observable<BandSearchResponse> getBandSearch(
            @Path("search_type") String searchType,
            @Path("keyword") String keyword,
            @Query("api_key") String apiKey
    );

    @GET(BAND_DETAIL_URL)
    Observable<BandDetailResponse> getBandDetails(
            @Path("band") String band,
            @Path("band_id") String bandId,
            @Query("api_key") String apiKey
    );
}
