package pedroscott.com.popularmoviesapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

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
public class DBQLiteHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_EXTERNAL_NAME = "movies.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_EXTERNAL_VERSION = 1;
    private Dao<Movie, Integer> movieDao;

    public DBQLiteHelper(Context context) {
        super(context, DATABASE_EXTERNAL_NAME, null, DATABASE_EXTERNAL_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        setMovieDao(null);
    }

    /**
     * Get the dao to access the movie table
     */
    public Dao<Movie, Integer> getMovieDao() throws SQLException {
        if (movieDao == null) {
            movieDao = getDao(Movie.class);
        }
        return movieDao;
    }

    public void setMovieDao(Dao<Movie, Integer> movieDao) {
        this.movieDao = movieDao;
    }

}
