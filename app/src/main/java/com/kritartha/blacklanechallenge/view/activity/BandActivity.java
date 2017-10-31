package com.kritartha.blacklanechallenge.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.view.fragment.BandDetailFragment;
import com.kritartha.blacklanechallenge.view.fragment.BandSearchFragment;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kritartha.blacklanechallenge.utils.AppUtils.addFragment;
import static com.kritartha.blacklanechallenge.utils.AppUtils.replaceFragment;
import static com.kritartha.blacklanechallenge.utils.Constants.PARCELABLE_SEARCH_RESULT;
import static com.kritartha.blacklanechallenge.utils.Constants.PARCELABLE_STORE_SEARCH_RESULT;
import static java.lang.Boolean.TRUE;

public class BandActivity extends AppCompatActivity implements
        BandSearchFragment.BandSearchEventListener, BandDetailFragment.BandDetailsEventListener {

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
        replaceFragment(fragmentManager, fragment, container, TRUE, BandSearchFragment.class.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if (backStackCount == 1) {
            finish();
            return;
        }
        super.onBackPressed();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void getBandDetails(SearchResult item, boolean store) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCELABLE_SEARCH_RESULT, item);
        bundle.putBoolean(PARCELABLE_STORE_SEARCH_RESULT, store);
        BandDetailFragment fragment = BandDetailFragment.newInstance(bundle);
        replaceFragment(fragmentManager, fragment, container, TRUE, BandDetailFragment.class.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}