package pedroscott.com.popularmoviesapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by android4 on 1/6/16.
 */
@Parcel
public class Movie {

    public static final String MOVIE = Movie.class.getName();
    @SerializedName("poster_path")
    private String posterPath;

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * @param posterPath The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
