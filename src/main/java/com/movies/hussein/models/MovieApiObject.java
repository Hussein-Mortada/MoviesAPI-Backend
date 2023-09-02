package com.movies.hussein.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieApiObject {

    private int limit;
    private int offset;
    private int total;

}