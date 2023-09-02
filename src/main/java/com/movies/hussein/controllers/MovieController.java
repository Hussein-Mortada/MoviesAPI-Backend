package com.movies.hussein.controllers;

import com.movies.hussein.models.Movie;
import com.movies.hussein.models.SearchRequest;
import com.movies.hussein.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/popular")
    public ResponseEntity<List<Movie>> fetchPopular() throws IOException {
        try{
            return ResponseEntity.ok(movieService.fetchPopularMovies().getResults());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Movie()));
        }

    }

    @GetMapping("/recent")
    public ResponseEntity<List<Movie>> fetchRecentMovies() throws IOException {
        try{
            return ResponseEntity.ok(movieService.fetchRecentMovies().getResults());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Movie()));
        }

    }

    @GetMapping("/random")
    public ResponseEntity<List<Movie>> fetchRandom() throws IOException {
        try{
            return ResponseEntity.ok(movieService.fetchRandom().getResults());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Movie()));
        }

    }

    @GetMapping("/movieid/{id}")
    public ResponseEntity<Movie> fetchById(@PathVariable Long id) throws IOException {


        try{
            System.out.println("responding okay");
            return ResponseEntity.ok(movieService.fetchById(id));
        }catch (Exception e){
            System.out.println("responding ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((new Movie()));
        }
    }


    @GetMapping("/search/{search}")
    public ResponseEntity<List<Movie>> fetchByTitle(@PathVariable String search) throws IOException {
        System.out.println("got request:"+ search);
        try{
            return ResponseEntity.ok(movieService.fetchTitles(search).getResults());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Movie()));
        }

    }



}
