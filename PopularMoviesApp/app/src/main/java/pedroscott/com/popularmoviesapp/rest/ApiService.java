package pedroscott.com.popularmoviesapp.rest;


import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by Pedro Scott
 */
public interface ApiService {
    //vote_average.desc
    @GET("3/discover/movie?sort_by=popularity.desc&api_key=bca3c1cc195e78964eb11133dfd62efe")
    Call<ResponseMovies> getMovies();
}
