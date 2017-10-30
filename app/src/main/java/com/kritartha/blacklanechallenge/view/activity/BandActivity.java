package com.kritartha.blacklanechallenge.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.view.fragment.BandDetailFragment;
import com.kritartha.blacklanechallenge.view.fragment.BandSearchFragment;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kritartha.blacklanechallenge.utils.AppUtils.addFragment;
import static com.kritartha.blacklanechallenge.utils.Constants.PARCELABLE_SEARCH_RESULT;
import static java.lang.Boolean.TRUE;

public class BandActivity extends AppCompatActivity implements
        BandSearchFragment.BandSearchEventListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;

    private FragmentManager fragmentManager;
    private int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_search);
        ButterKnife.bind(this);
        setupToolbar();
        setFragmentManager();
        setupBandSearchFragment();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
    }

    private void setFragmentManager() {
        container = R.id.content_frame;
        fragmentManager = getSupportFragmentManager();
    }

    private void setupBandSearchFragment() {
        BandSearchFragment fragment = BandSearchFragment.newInstance(new Bundle());
        addFragment(fragmentManager, fragment, container, TRUE, BandSearchFragment.class.getName());
    }

    @Override
    public void onBackPressed() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if (backStackCount == 1) {
            finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void getBandDetails(SearchResult item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCELABLE_SEARCH_RESULT, item);
        BandDetailFragment fragment = BandDetailFragment.newInstance(bundle);
        addFragment(fragmentManager, fragment, container, TRUE, BandDetailFragment.class.getName());
    }
}