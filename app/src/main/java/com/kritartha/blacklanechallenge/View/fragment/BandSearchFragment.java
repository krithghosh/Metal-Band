package com.kritartha.blacklanechallenge.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kritartha.blacklanechallenge.presenter.BandSearchPresenter;
import com.kritartha.blacklanechallenge.MetalBandApplication;
import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.view.BandSearchContract;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.kritartha.blacklanechallenge.utils.RxSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BandSearchFragment extends Fragment implements BandSearchContract.View,
        TextWatcher,
        View.OnClickListener,
        View.OnFocusChangeListener,
        AdapterView.OnItemClickListener {

    @BindView(R.id.ll_parent_layout)
    LinearLayout llParentLayout;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.lv_search)
    ListViewCompat lvSearch;

    @BindView(R.id.rv_history)
    RecyclerView rvHistory;

    @Inject
    BandSearchPresenter mPresenter;

    private List<SearchResult> searchResults = null;
    private List<String> names = null;
    private static final int DEB_TIME = 300;
    private ArrayAdapter<String> dataAdapter = null;
    private BandSearchEventListener mEventListener = null;

    public BandSearchFragment() {
    }

    public interface BandSearchEventListener {
        void getBandDetails(SearchResult item);
    }

    public static BandSearchFragment newInstance(Bundle bundle) {
        BandSearchFragment fragment = new BandSearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mEventListener = (BandSearchEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectModules();
    }

    @Override
    public void injectModules() {
        MetalBandApplication.getAppComponent().inject(this);
        mPresenter.setView(this);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSearchList(List<SearchResult> searchResults, List<String> names) {
        mPresenter.unSubscribeBandSearch();
        this.searchResults = searchResults;
        this.names = names;
        dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.search_suggestion_text,
                new ArrayList<>(names));
        lvSearch.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_band_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
        setupSearchView();
    }

    private void setupAdapter() {
        RxSearch.fromEditText(etSearch)
                .debounce(DEB_TIME, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() >= 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dataAdapter.clear();
                        dataAdapter.notifyDataSetChanged();
                        showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        mPresenter.getBandSearch(s);
                    }
                });
        etSearch.setOnClickListener(this);

        dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.search_suggestion_text,
                new ArrayList<>());
        lvSearch.setAdapter(dataAdapter);
        lvSearch.setOnItemClickListener(this);
    }

    private void setupSearchView() {
        VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(
                getResources(), R.drawable.ic_search, null);
        etSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableCompat, null, null, null);
        etSearch.setHint(getResources().getString(R.string.suggestion_text));
        etSearch.addTextChangedListener(this);
        etSearch.setOnFocusChangeListener(this);
        llParentLayout.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() < 2) {
            if (dataAdapter != null) {
                dataAdapter.clear();
                dataAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_parent_layout:
                etSearch.clearFocus();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            hideSoftKeyboard(v);
            dataAdapter.clear();
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchResult item = searchResults.get(position);
        mEventListener.getBandDetails(item);
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
