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
import com.kritartha.blacklanechallenge.adapter.BandAlbumAdapter;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailResponse;
import com.kritartha.blacklanechallenge.model.bandDetail.BandDetailsData;
import com.kritartha.blacklanechallenge.model.bandDetail.Discography;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kritarthaghosh on 31/10/17.
 */

public class BandDetailComponentCustomView extends LinearLayout {

    private RecyclerView recyclerView;
    private TextView tvNameHead;
    private TextView tvGenreHead;
    private TextView tvCountryHead;
    private TextView tvYearHead;
    private TextView tvName;
    private TextView tvGenre;
    private TextView tvCountry;
    private TextView tvYear;
    private TextView tvAlbumHeading;
    private BandDetailResponse bandDetailResponse;
    private BandAlbumAdapter mAdapter;
    private List<Discography> mAlbum;

    public BandDetailComponentCustomView(Context context, BandDetailResponse bandDetailResponse) {
        super(context);
        this.mAlbum = new ArrayList<>();
        this.bandDetailResponse = bandDetailResponse;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_view_band_album, this);
        LinearLayout llFirstComponent = (LinearLayout) findViewById(R.id.view_first_component);
        LinearLayout llSecondComponent = (LinearLayout) findViewById(R.id.view_second_component);
        tvAlbumHeading = (TextView) findViewById(R.id.tv_album);
        tvNameHead = (TextView) llFirstComponent.findViewById(R.id.tv_lt_primary);
        tvName = (TextView) llFirstComponent.findViewById(R.id.tv_lt_secondary);
        tvGenreHead = (TextView) llFirstComponent.findViewById(R.id.tv_rt_primary);
        tvGenre = (TextView) llFirstComponent.findViewById(R.id.tv_rt_secondary);
        tvCountryHead = (TextView) llSecondComponent.findViewById(R.id.tv_lt_primary);
        tvCountry = (TextView) llSecondComponent.findViewById(R.id.tv_lt_secondary);
        tvYearHead = (TextView) llSecondComponent.findViewById(R.id.tv_rt_primary);
        tvYear = (TextView) llSecondComponent.findViewById(R.id.tv_rt_secondary);
        recyclerView = (RecyclerView) findViewById(R.id.rv_albums);
        setupAdapter(context);
        setupRecyclerView(context);
    }

    private void setupAdapter(Context context) {
        mAdapter = new BandAlbumAdapter(context, mAlbum);
        mAdapter.setOnItemClickListener(new BandAlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Discography item) {

            }
        });
    }

    private void setupRecyclerView(Context context) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void updateData(BandDetailResponse bandDetailResponse) {
        this.bandDetailResponse = bandDetailResponse;
        tvNameHead.setText("Band Name");
        tvGenreHead.setText("Genre");
        tvCountryHead.setText("Country");
        tvYearHead.setText("Active Years");

        BandDetailsData data = bandDetailResponse.getData();
        tvName.setText(data.getBandName());
        tvGenre.setText(data.getDetails().getGenre());
        tvCountry.setText(data.getDetails().getCountryOfOrigin());
        tvYear.setText(data.getDetails().getYearsActive());

        List<Discography> list = data.getDiscography();
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.size() == 0) {
            tvAlbumHeading.setText("No Albums");
        }
        mAdapter.updateList(list);
        mAdapter.notifyDataSetChanged();
    }
}
