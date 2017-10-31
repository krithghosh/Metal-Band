package com.kritartha.blacklanechallenge.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kritartha.blacklanechallenge.MetalBandApplication;
import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.presenter.BandDetailPresenter;
import com.kritartha.blacklanechallenge.view.BandDetailContract;
import com.kritartha.blacklanechallenge.view.customview.BandDetailComponentCustomView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kritartha.blacklanechallenge.utils.Constants.PARCELABLE_SEARCH_RESULT;

public class BandDetailFragment extends Fragment implements
        BandDetailContract.View {

    @BindView(R.id.iv_band)
    ImageView ivBand;

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;

    @Inject
    BandDetailPresenter mPresenter;

    private SearchResult searchResult = null;
    private BandDetailsEventListener mEventListener = null;
    private BandDetailComponentCustomView customView;

    public BandDetailFragment() {
    }

    public interface BandDetailsEventListener {
        void showError(String msg);
    }

    public static BandDetailFragment newInstance(Bundle bundle) {
        BandDetailFragment fragment = new BandDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mEventListener = (BandDetailsEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectModules();
        getDefaultArguments(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_band_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (searchResult == null) {
            return;
        }
        setupBandDetailComponentCustomView();
        mPresenter.getBandDetail(searchResult.getId());
    }

    private void setupBandDetailComponentCustomView() {
        customView = new BandDetailComponentCustomView(getContext(), null);
        contentFrame.addView(customView);
    }

    private void getDefaultArguments(Bundle bundle) {
        if (bundle == null)
            return;
        searchResult = bundle.getParcelable(PARCELABLE_SEARCH_RESULT);
        if (searchResult != null) {
            mPresenter.storeBandSearch(searchResult);
        }
    }

    @Override
    public void showError(String msg) {
        mPresenter.unSubscribeBandDetail();
        mEventListener.showError(msg);
    }

    @Override
    public void injectModules() {
        MetalBandApplication.getAppComponent().inject(this);
        mPresenter.setView(this);
    }

    @Override
    public void updateBandDetail(BandDetailResponse bandDetailResponse) {
        mPresenter.unSubscribeBandDetail();
        Picasso.with(getContext())
                .load(bandDetailResponse.getData().getPhoto())
                .noPlaceholder()
                .fit()
                .into(ivBand);
        customView.updateData(bandDetailResponse);
    }
}
