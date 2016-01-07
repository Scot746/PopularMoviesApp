package pedroscott.com.popularmoviesapp.app.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.App;
import pedroscott.com.popularmoviesapp.app.ui.home.adapter.AdapterMovies;
import pedroscott.com.popularmoviesapp.model.Movie;
import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovies;
import pedroscott.com.popularmoviesapp.utils.DebugUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    private AdapterMovies adapter;
    private GridLayoutManager layoutManager;
    private int page;
    public ArrayList<Movie> movies;

    @Bind(R.id.rVHome)
    RecyclerView rVHome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
    }

    private void initVars() {
        adapter = new AdapterMovies(new ArrayList<Movie>(),getActivity());
        loadMovies(getString(R.string.sort_by_popularity));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        intViews();
        return view;
    }

    private void intViews() {
        layoutManager = new GridLayoutManager(getActivity(), 2);
        rVHome.setLayoutManager(layoutManager);
        rVHome.setAdapter(adapter);
    }

    private void loadMovies(String sort) {
        App.getRestClientPublic().getPublicService()
                .getMovies(sort,getString(R.string.api_key_themoviedb))
                .enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Response<ResponseMovies> response, Retrofit retrofit) {
                movies = response.body().getResults();
                adapter.setData(movies);
                page = response.body().getPage();
                DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
            }

            @Override
            public void onFailure(Throwable t) {
                DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);

            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                getActivity().onBackPressed();
                return true;
            }
            case R.id.action_popularity: {
                adapter.getItems().clear();
                loadMovies(getString(R.string.sort_by_popularity));
                return true;
            }
            case R.id.action_vote: {
                adapter.getItems().clear();
                loadMovies(getString(R.string.sort_by_vote));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
