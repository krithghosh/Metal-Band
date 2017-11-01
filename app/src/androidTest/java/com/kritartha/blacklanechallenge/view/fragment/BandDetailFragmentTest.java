package com.kritartha.blacklanechallenge.view.fragment;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetails;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailsData;
import com.kritartha.blacklanechallenge.model.bandDetail.Discography;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.view.activity.BandActivity;
import com.kritartha.blacklanechallenge.view.customview.BandDetailComponentCustomView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;

/**
 * Created by kritarthaghosh on 01/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class BandDetailFragmentTest {

    private BandActivity mActivity;
    BandDetailFragment mFragment;
    private String BAND_NAME = "Pink Floyd";
    private String BAND_COUNTRY = "England";
    private String BAND_GENRE = "Progressive Rock";
    private String BAND_ACTIVE_YEAR = "1970-Present";

    @Rule
    public ActivityTestRule<BandActivity> activityActivityTestRule = new
            ActivityTestRule<BandActivity>(BandActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        this.activityActivityTestRule.launchActivity(new Intent());
        mActivity = this.activityActivityTestRule.getActivity();
        SearchResult searchResult = Mockito.mock(SearchResult.class);
        mActivity.runOnUiThread(() -> {
            mActivity.getBandDetails(searchResult, TRUE);
            this.mFragment = (BandDetailFragment) mActivity.getSupportFragmentManager()
                    .findFragmentById(R.id.content_frame);
        });
    }

    @Test
    public void updateBandDetail_setsBandDetailData() {

        // Mocks Data
        BandDetailResponse response = new BandDetailResponse();
        BandDetailsData data = new BandDetailsData();
        BandDetails details = new BandDetails();
        Discography discography = Mockito.mock(Discography.class);
        List<Discography> list = new ArrayList<>();
        list.add(discography);
        data.setBandName(BAND_NAME);
        details.setLocation(BAND_COUNTRY);
        details.setGenre(BAND_GENRE);
        details.setYearsActive(BAND_ACTIVE_YEAR);
        data.setDetails(details);
        data.setDiscography(list);
        response.setData(data);

        BandDetailComponentCustomView customView = mFragment.getBandDetailComponentCustomView();
        RecyclerView recyclerView = (RecyclerView) customView.findViewById(R.id.rv_albums);

        mActivity.runOnUiThread(() -> {
            mFragment.updateBandDetail(response);
            onView(withText(BAND_NAME)).check(matches(isDisplayed()));
            onView(withText(BAND_COUNTRY)).check(matches(isDisplayed()));
            onView(withText(BAND_GENRE)).check(matches(isDisplayed()));
            onView(withText(BAND_GENRE)).check(matches(isDisplayed()));

            assertEquals(recyclerView.getAdapter().getItemCount(), 1);
        });
    }
}