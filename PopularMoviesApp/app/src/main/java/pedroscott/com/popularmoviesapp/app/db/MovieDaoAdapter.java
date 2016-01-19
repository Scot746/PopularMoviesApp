package pedroscott.com.popularmoviesapp.app.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import pedroscott.com.popularmoviesapp.app.App;
import pedroscott.com.popularmoviesapp.model.Movie;


/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>8
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class MovieDaoAdapter {

    /**
     * Get get all movies
     */
    public static ArrayList<Movie> getAllMovies(Context context) throws SQLException {
        DBQLiteHelper dbHelper = App.getDBExternalHelper(context);
        Dao<Movie, Integer> movieDao = dbHelper.getMovieDao();
        ArrayList<Movie> movies = new ArrayList<Movie>(movieDao.queryForAll());
        return movies;
    }

    /**
     * insert movie
     */
    public static int insertMovies(Context context, Movie movie) throws SQLException {
        DBQLiteHelper dbHelper = App.getDBExternalHelper(context);
        Dao<Movie, Integer> movieDao = dbHelper.getMovieDao();
        return movieDao.create(movie);
    }

    /**
     * remove movie
     */
    public static int removeMovies(Context context, Movie movie) throws SQLException {
        DBQLiteHelper dbHelper = App.getDBExternalHelper(context);
        Dao<Movie, Integer> movieDao = dbHelper.getMovieDao();
        return movieDao.delete(movie);
    }

    /**
     * validate if the movie is in the database
     */
    public static boolean isMovieFavorite(Context context, int id) throws SQLException {
        DBQLiteHelper dbHelper = App.getDBExternalHelper(context);
        Dao<Movie, Integer> movieDao = dbHelper.getMovieDao();
        Movie movie = movieDao.queryForId(id);
        return (movie != null && movie.getId() == id);
    }

    /**
     * abstract the implementation to set or remove favorite
     */
    public static boolean favoriteMovie(Context context, Movie movie) throws SQLException {
        if (isMovieFavorite(context, movie.getId())) {
            removeMovies(context, movie);
            return false;
        } else {
            insertMovies(context, movie);
            return true;
        }
    }

}
