package com.kritartha.blacklanechallenge.model.bandSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kritarthaghosh on 26/10/17.
 */

public class BandData {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("search_results")
    @Expose
    private List<SearchResult> searchResults = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }
}
