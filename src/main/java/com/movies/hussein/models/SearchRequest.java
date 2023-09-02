package com.movies.hussein.models;

import jakarta.validation.constraints.NotBlank;

public class SearchRequest {
    @NotBlank(message = "Search is required!")
    private String search;

    public String getSearch() {
        //search = search.replaceAll("[^a-zA-Z0-9]", " ");
        //search = search.replaceAll("\\s+", " ").trim();

        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
