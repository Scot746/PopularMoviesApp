package pedroscott.com.popularmoviesapp.rest;

import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import retrofit.Call;


/**
 * Created by Pedro Scott
 */
public class PublicService {

    private ApiService apiService;

    public PublicService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<ResponseMovies> getMovies(String sortBy, String key) {
        return apiService.getMovies(sortBy,key);
    }
}
