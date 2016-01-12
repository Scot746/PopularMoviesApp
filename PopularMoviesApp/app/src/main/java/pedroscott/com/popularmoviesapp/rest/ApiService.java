package pedroscott.com.popularmoviesapp.rest;


import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

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
public interface ApiService {

    @GET("3/discover/movie")
    Call<ResponseMovies> getMovies(@Query("sort_by") String sort_by,
                                   @Query("api_key") String key);
}
