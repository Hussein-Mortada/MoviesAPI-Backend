CREATE SCHEMA IF NOT EXISTS movie_review_app;

USE movie_review_app;

CREATE TABLE IF NOT EXISTS users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
                                     salt VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS reviews (
                                       review_id INT AUTO_INCREMENT PRIMARY KEY,
                                       movie_api_id VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    review_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX idx_reviews_movie_api_id ON reviews(movie_api_id);

