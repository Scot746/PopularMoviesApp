package pedroscott.com.popularmoviesapp.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import pedroscott.com.popularmoviesapp.model.Movie;

public class DBQLiteHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_EXTERNAL_NAME = "movies.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_EXTERNAL_VERSION = 1;
    private Dao<Movie,Integer> movieDao;

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
