package com.kritartha.blacklanechallenge.model.bandDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kritarthaghosh on 29/10/17.
 */

public class BandDetails {

    @SerializedName("country of origin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("formed in")
    @Expose
    private String formedIn;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("lyrical themes")
    @Expose
    private String lyricalThemes;
    @SerializedName("current label")
    @Expose
    private String currentLabel;
    @SerializedName("years active")
    @Expose
    private String yearsActive;

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormedIn() {
        return formedIn;
    }

    public void setFormedIn(String formedIn) {
        this.formedIn = formedIn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLyricalThemes() {
        return lyricalThemes;
    }

    public void setLyricalThemes(String lyricalThemes) {
        this.lyricalThemes = lyricalThemes;
    }

    public String getCurrentLabel() {
        return currentLabel;
    }

    public void setCurrentLabel(String currentLabel) {
        this.currentLabel = currentLabel;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(String yearsActive) {
        this.yearsActive = yearsActive;
    }
}
