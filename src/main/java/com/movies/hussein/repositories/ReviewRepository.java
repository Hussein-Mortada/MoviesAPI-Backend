package com.movies.hussein.repositories;

import com.movies.hussein.models.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewsEntity,Integer> {
    List<ReviewsEntity> findAllByMovieApiId(String movieApiId);

    List<ReviewsEntity> findAllByRatingGreaterThanEqual(int rating);

    List<ReviewsEntity> findAllByReviewTextContaining(String keyword);

    List<ReviewsEntity> findAllByCreatedAtBetween(Timestamp startDate, Timestamp endDate);

}
