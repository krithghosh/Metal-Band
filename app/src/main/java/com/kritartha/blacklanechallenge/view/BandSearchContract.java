package com.kritartha.blacklanechallenge.view;

import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import java.util.List;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public interface BandSearchContract {
    interface View {
        void showError(String msg);

        void injectModules();

        void updateSearchList(List<SearchResult> searchResults, List<String> names);

        void updateSearchHistoryList(List<SearchResult> searchResults);
    }

    interface Presenter {
        void setView(BandSearchContract.View mView);

        void unSubscribeBandSearch();

        void getBandSearch(String keyword);

        void unSubscribeBandHistory();

        void getBandHistory();
    }
}
