package pedroscott.com.popularmoviesapp.rest;

import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovieReviews;
import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovieTrailers;
import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import retrofit.Call;

/**
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class PublicService {

    private ApiService apiService;

    public PublicService(ApiService apiService) {
        this.apiService = apiService;

    }

    /**
     * Get the movies to show with the sortBy to define the list of movies that you want.
     */
    public Call<ResponseMovies> getMovies(String sortBy ) {
        return apiService.getMovies(sortBy);
    }

    /**
     * Get the trailers of a movie.
     */
    public Call<ResponseMovieTrailers> getMovieTrailers(int id) {
        return apiService.getMovieTrailers(id);
    }


    /**
     * Get the reviews of a movie.
     */
    public Call<ResponseMovieReviews> getMovieReviews(int id) {
        return apiService.getMovieReviews(id);
    }
}
