package com.movies.hussein.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieApiResponse {

    @JsonProperty("Object")
    private MovieApiObject object;

    @JsonProperty("results")
    private List<Movie> results;

    public MovieApiObject getObject() {
        return object;
    }

    public void setObject(MovieApiObject object) {
        this.object = object;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}


