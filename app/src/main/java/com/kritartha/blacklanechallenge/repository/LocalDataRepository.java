package com.kritartha.blacklanechallenge.repository;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kritartha.blacklanechallenge.MetalBandApplication;
import com.kritartha.blacklanechallenge.database.BandMetadataTable;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class LocalDataRepository {
    private static final String TAG = "LocalDataRepository";

    @Inject
    BriteDatabase db;

    public LocalDataRepository() {
        MetalBandApplication.getAppComponent().inject(this);
    }

    public Observable<List<SearchResult>> getBandMetadata() {
        return db.createQuery(BandMetadataTable.TABLE, BandMetadataTable.QUERY_ALL)
                .mapToList(BandMetadataTable.Mapper);
    }

    public void insertBandMetadata(SearchResult item) {
        BriteDatabase.Transaction transaction = db.newTransaction();
        try {
            db.insert(BandMetadataTable.TABLE, new BandMetadataTable.Builder()
                    .setBandId(item.getId())
                    .setBandName(item.getName())
                    .setGenre(item.getGenre())
                    .setCountry(item.getCountry())
                    .build(), SQLiteDatabase.CONFLICT_REPLACE);
            transaction.markSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "insertBandMetadata: ", e);
        } finally {
            transaction.end();
        }
    }

    public void deleteBandMetadata() {
        try {
            db.delete(BandMetadataTable.TABLE, "1", null);
        } catch (Exception e) {
            Log.e(TAG, "deleteBandMetadata: ", e);
        }
    }
}
