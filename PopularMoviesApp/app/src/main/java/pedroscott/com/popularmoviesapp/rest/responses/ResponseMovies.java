package pedroscott.com.popularmoviesapp.rest.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import pedroscott.com.popularmoviesapp.model.Movie;

/**
 * Created by android4 on 1/6/16.
 */
public class ResponseMovies implements Serializable {

    private static final long serialVersionUID = -2161979058014097219L;

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private ArrayList<Movie> results;

    @SerializedName("total_results")
    @Expose
    private long totalResults;

    @SerializedName("total_pages")
    @Expose
    private long totalPages;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
