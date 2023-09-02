package com.movies.hussein.models;

import jakarta.validation.constraints.NotBlank;

public class ReviewCreateRequest {

    @NotBlank(message = "Movie ID is required!")
    private String movie_api_id;
    @NotBlank(message = "Rating is required!")
    private int rating;
    @NotBlank(message = "Review Text is required!")
    private String review_text;

    public String getMovie_api_id() {
        return movie_api_id;
    }

    public void setMovie_api_id(String movie_api_id) {
        this.movie_api_id = movie_api_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }
}
