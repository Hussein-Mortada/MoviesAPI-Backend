package com.movies.hussein.controllers;

import com.movies.hussein.models.Movie;
import com.movies.hussein.models.ReviewCreateRequest;
import com.movies.hussein.models.ReviewsEntity;
import com.movies.hussein.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/todaysReviews")
    public ResponseEntity<List<ReviewsEntity>> getTodayReviews() {
        List<ReviewsEntity> reviews = reviewService.findAllByCreatedToday();

        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build(); // No reviews were made today
        } else {
            return ResponseEntity.ok(reviews);
        }
    }

    @GetMapping("/movieid/{id}")
    public ResponseEntity<List<ReviewsEntity>> fetchById(@PathVariable String id) throws IOException {
        System.out.println("got request for review:"+ id);

        try{
            System.out.println("responding okay");
            return ResponseEntity.ok(reviewService.findAllByMovieApiId((id)));
        }catch (Exception e){
            System.out.println("responding ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((Collections.singletonList(new ReviewsEntity())));
        }
    }


    @PostMapping("/createReview")
    public ResponseEntity<String> createReview(@RequestBody ReviewCreateRequest request){
        System.out.println("Received create review request: ");
        System.out.println(request.getMovie_api_id() + "ID");
        System.out.println(request.getRating() + "rating");
        System.out.println(request.getReview_text() + "text");

        String movieId= request.getMovie_api_id();
        int rating = request.getRating();
        String reviewText = request.getReview_text();

        boolean success =reviewService.createReview(movieId,rating,reviewText);

        if(success){
            return ResponseEntity.ok("Review Created!");
        }else{
            return ResponseEntity.ok("Review Creation Failed!");
        }

    }

}
