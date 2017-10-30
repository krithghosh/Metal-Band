package com.kritartha.blacklanechallenge.view;

import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public interface BandDetailContract {
    interface View {
        void showError(String msg);

        void injectModules();

        void updateBandDetail(BandDetailResponse bandDetailResponse);
    }

    interface Presenter {
        void setView(BandDetailContract.View mView);

        void storeBandSearch(SearchResult searchResult);

        void unSubscribeBandDetail();

        void getBandDetail(String id);
    }
}
