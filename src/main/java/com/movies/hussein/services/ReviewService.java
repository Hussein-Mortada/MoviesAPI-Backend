package com.movies.hussein.services;

import com.movies.hussein.models.ReviewsEntity;
import com.movies.hussein.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    public List<ReviewsEntity> getAll(){
        return reviewRepository.findAll();
    }


    public List<ReviewsEntity> findAllByMovieApiId(String movieApiId){
        return  reviewRepository.findAllByMovieApiId(movieApiId);
    }

    public List<ReviewsEntity> findAllByCreatedAtBetween(Timestamp startDate, Timestamp endDate){
        return  reviewRepository.findAllByCreatedAtBetween(startDate,endDate);
    }

    public List<ReviewsEntity> findAllByCreatedToday() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = LocalDate.now().atTime(LocalTime.MAX);

        Timestamp startTimestamp = Timestamp.valueOf(todayStart);
        Timestamp endTimestamp = Timestamp.valueOf(todayEnd);

        return reviewRepository.findAllByCreatedAtBetween(startTimestamp, endTimestamp);
    }

    public boolean createReview(String movieApiId, int rating, String review_text){
        ReviewsEntity review = new ReviewsEntity();
        review.setMovieApiId(movieApiId);
        review.setRating(rating);
        review.setReviewText(review_text);
        LocalDateTime now = LocalDate.now().atStartOfDay();
        Timestamp startTimestamp = Timestamp.valueOf(now);
        review.setCreatedAt(startTimestamp);
        reviewRepository.save(review);
        return true;
    }

    public List<ReviewsEntity> findAll() {
        return reviewRepository.findAll();
    }
}
