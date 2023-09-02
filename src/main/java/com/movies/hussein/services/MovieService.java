package com.movies.hussein.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.hussein.models.Movie;
import com.movies.hussein.models.MovieApiResponse;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class MovieService {

    private static final String API_BASE_URL = "https://api.apilayer.com/unogs";
    @Value("${MOVIE_API_KEY}")
    private String API_KEY;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MovieApiResponse fetchPopularMovies() throws IOException {
        String apiUrl = buildApiUrl("rating", 2018, 5, null);

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", API_KEY)
                .build();

        return executeRequest(request);
    }

    public MovieApiResponse fetchRecentMovies() throws IOException {
        String apiUrl = buildApiUrl("date", 2023, 5, null);

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", API_KEY)
                .build();

        return executeRequest(request);
    }

    public Movie fetchById(Long netflixID)throws IOException{
        String apiUrl = API_BASE_URL + "/title/details?netflix_id="+netflixID;
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", API_KEY)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return objectMapper.readValue(response.body().byteStream(), Movie.class);
        }
    }


    public MovieApiResponse fetchRandom() throws IOException {
        String apiUrl = buildApiUrl("rating", 2000, 5, "random");

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", API_KEY)
                .build();

        return executeRequest(request);
    }

    public MovieApiResponse fetchTitles(String title) throws IOException {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String apiUrl = buildApiUrl("rating", 2000, 25, encodedTitle);

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", API_KEY)
                .build();

        return executeRequest(request);
    }

    private String buildApiUrl(String orderBy, int startYear, int limit, String title) {
        StringBuilder apiUrlBuilder = new StringBuilder(API_BASE_URL + "/search/titles?type=movie");
        apiUrlBuilder.append("&order_by=").append(orderBy);
        apiUrlBuilder.append("&start_year=").append(startYear);
        apiUrlBuilder.append("&limit=").append(limit);

        if (title != null && !title.equals("random")) {
            apiUrlBuilder.append("&title=").append(title);
        }

        if(title != null && title.equals("random")){
            apiUrlBuilder.append("&offset=").append((int)((Math.random()+1) *5000 ));
        }

        return apiUrlBuilder.toString();
    }

    private MovieApiResponse executeRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return objectMapper.readValue(response.body().byteStream(), MovieApiResponse.class);
        }
    }
}
