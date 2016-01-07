package pedroscott.com.popularmoviesapp.rest;


import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by Pedro Scott
 */
public interface ApiService {

    @GET("3/discover/movie")
    Call<ResponseMovies> getMovies(@Query("sort_by") String sort_by,
                                   @Query("api_key") String key);
}
