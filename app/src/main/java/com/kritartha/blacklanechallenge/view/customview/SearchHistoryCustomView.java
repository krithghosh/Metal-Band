package com.kritartha.blacklanechallenge.view.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.adapter.SearchHistoryAdapter;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class SearchHistoryCustomView extends LinearLayout {

    private SearchHistoryEventListener mEventListener;
    private List<SearchResult> searchResultList;
    private SearchHistoryAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView tvSearchHistoryHeading;

    public interface SearchHistoryEventListener {
        void onSearchHistoryBandSelected(SearchResult searchResult);
    }

    public SearchHistoryCustomView(Context context, SearchHistoryEventListener mEventListener,
                                   List<SearchResult> searchResultList) {
        super(context);
        this.searchResultList = new ArrayList<>();
        this.mEventListener = mEventListener;
        this.searchResultList = searchResultList;
        init(context);
    }

    public SearchHistoryCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchHistoryCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_view_search_history, this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_history);
        tvSearchHistoryHeading = (TextView) findViewById(R.id.tv_search_history_heading);
        setupEventListAdapter(context);
        setupRecyclerView(context);
    }

    private void setupEventListAdapter(Context context) {
        mAdapter = new SearchHistoryAdapter(context, searchResultList);
        mAdapter.setOnItemClickListener(new SearchHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SearchResult item) {
                mEventListener.onSearchHistoryBandSelected(item);
            }
        });
    }

    private void setupRecyclerView(Context context) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void updateList(List<SearchResult> searchResults) {
        if (searchResults == null) {
            searchResults = new ArrayList<>();
        }
        if (searchResults.size() == 0) {
            tvSearchHistoryHeading.setText("No Search History");
        }
        mAdapter.updateList(searchResults);
        mAdapter.notifyDataSetChanged();
    }
}
