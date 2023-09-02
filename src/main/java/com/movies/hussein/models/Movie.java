package com.movies.hussein.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("imdb_id")
    private String imdbId;


    @JsonProperty("netflix_id")
    private long netflixId;
    @JsonProperty("default_image")
    private String default_image;

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getLarge_image() {
        return large_image;
    }

    public void setLarge_image(String large_image) {
        this.large_image = large_image;
    }

    @JsonProperty("large_image")
    private String large_image;

    private String poster;
    private String rating;
    private String runtime;
    private String synopsis;
    private String title;

    @JsonProperty("title_date")
    private String titleDate;

    @JsonProperty("title_type")
    private String titleType;

    private int top250;
    private int top250tv;
    private String year;
    @JsonProperty("start_date")
    private String start_date;

    @JsonProperty("maturity_label")
    private String maturity_label;


    private String img;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getNetflixId() {
        return netflixId;
    }

    public void setNetflixId(long netflixId) {
        this.netflixId = netflixId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDate() {
        return titleDate;
    }

    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public int getTop250() {
        return top250;
    }

    public void setTop250(int top250) {
        this.top250 = top250;
    }

    public int getTop250tv() {
        return top250tv;
    }

    public void setTop250tv(int top250tv) {
        this.top250tv = top250tv;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}