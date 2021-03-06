package com.kritartha.blacklanechallenge.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import rx.functions.Func1;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class BandMetadataTable {

    private static final String TAG = "BandMetadataTable";

    public static final String TABLE = "band_metadata";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GENRE = "genre";
    public static final String COUNTRY = "country";

    public static final String QUERY_ALL = "SELECT " + ID + ", " + NAME + ", " + GENRE + ", "
            + COUNTRY + " FROM " + TABLE;

    public static final String CREATE_TABLE = ""
            + "CREATE TABLE " + TABLE + "("
            + ID + " TEXT PRIMARY KEY NOT NULL,"
            + NAME + " TEXT,"
            + GENRE + " TEXT,"
            + COUNTRY + " TEXT"
            + ")";

    public static final class Builder {
        private final ContentValues contentValues = new ContentValues();

        public Builder setBandId(String value) {
            contentValues.put(ID, value);
            return this;
        }

        public Builder setBandName(String value) {
            contentValues.put(NAME, value);
            return this;
        }

        public Builder setGenre(String value) {
            contentValues.put(GENRE, value);
            return this;
        }

        public Builder setCountry(String value) {
            contentValues.put(COUNTRY, value);
            return this;
        }

        public ContentValues build() {
            return contentValues;
        }
    }

    public static final Func1<Cursor, SearchResult> Mapper = new Func1<Cursor, SearchResult>() {
        @Override
        public SearchResult call(Cursor cursor) {
            SearchResult obj = new SearchResult();
            obj.setId(Db.getString(cursor, ID));
            obj.setName(Db.getString(cursor, NAME));
            obj.setGenre(Db.getString(cursor, GENRE));
            obj.setCountry(Db.getString(cursor, COUNTRY));
            return obj;
        }
    };
}