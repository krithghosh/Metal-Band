package com.kritartha.blacklanechallenge.model.databaseModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

@Entity(tableName = "BandMetadata")
public class BandMetadata {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "band_id")
    private String bandId;

    @ColumnInfo(name = "band_name")
    private String bandName;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "country")
    private String country;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
