package com.kritartha.blacklanechallenge.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class RxSearch {

    public static Observable<String> fromSearchView(@NonNull final SearchView searchView) {
        final BehaviorSubject<String> subject = BehaviorSubject.create("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onCompleted();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    subject.onNext(newText);
                }
                return true;
            }
        });

        return subject;
    }

    public static Observable<String> fromEditText(@NonNull final EditText editText) {
        final BehaviorSubject<String> subject = BehaviorSubject.create("");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence query, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable query) {
                String newText = query.toString();
                if (!newText.isEmpty()) {
                    subject.onNext(newText);
                }
                return;
            }
        });

        return subject;
    }
}
