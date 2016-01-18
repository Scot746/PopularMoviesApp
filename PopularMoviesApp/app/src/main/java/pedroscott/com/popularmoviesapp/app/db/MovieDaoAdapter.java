package pedroscott.com.popularmoviesapp.app.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import pedroscott.com.popularmoviesapp.app.App;
import pedroscott.com.popularmoviesapp.model.Movie;

/**
 * Created by android4 on 1/18/16.
 */
public class MovieDaoAdapter {


    public static ArrayList<Movie> getAllMovies(Context context) throws SQLException {
        DBQLiteHelper dbHelper = App.getDBExternalHelper(context);
        Dao<Movie, Integer> movieDao = dbHelper.getMovieDao();
        ArrayList<Movie> movies = new ArrayList<Movie>(movieDao.queryForAll());
        return movies;
    }


}
