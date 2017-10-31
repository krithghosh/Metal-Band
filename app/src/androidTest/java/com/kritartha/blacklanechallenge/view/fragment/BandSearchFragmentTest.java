package com.kritartha.blacklanechallenge.view.fragment;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.adapter.SearchHistoryAdapter;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.view.activity.BandActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by kritarthaghosh on 31/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class BandSearchFragmentTest {

    private BandActivity mActivity;
    BandSearchFragment mFragment;

    @Rule
    public ActivityTestRule<BandActivity> activityActivityTestRule = new
            ActivityTestRule<BandActivity>(BandActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        this.activityActivityTestRule.launchActivity(new Intent());
        mActivity = this.activityActivityTestRule.getActivity();
        this.mFragment = (BandSearchFragment) mActivity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
    }

    @Test
    public void testFragment() throws Exception {
        assertNotNull(mFragment);
    }

    @Test
    public void testFragmentBandSearch() {
        EditText etSearch = (EditText) mFragment.getView().findViewById(R.id.et_search);
        assert (etSearch.getText().equals(""));
    }

    @Test
    public void updateSearchHistoryList_setsListOfBands() {
        RecyclerView recyclerView = (RecyclerView) mFragment.getView().findViewById(R.id.rv_history);

        // Mocks Data
        List<SearchResult> items = new ArrayList<>();
        SearchResult searchResult = new SearchResult();
        searchResult.setName("Megadeth");
        searchResult.setGenre("Heavy Metal");
        searchResult.setCountry("US");
        items.add(searchResult);

        SearchResult searchResultOther = new SearchResult();
        searchResult.setName("Led Zeppelin");
        searchResult.setGenre("Rock");
        searchResult.setCountry("UK");
        items.add(searchResult);

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                mFragment.updateSearchHistoryList(items);
                assertEquals(recyclerView.getAdapter().getItemCount(), 2);
            }
        });
    }
}