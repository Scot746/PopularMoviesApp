package pedroscott.com.popularmoviesapp.app.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import pedroscott.com.popularmoviesapp.utils.EndlessRecyclerOnScrollListener;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>
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
public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private AdapterMovies adapter;
    private GridLayoutManager layoutManager;
    public ArrayList<Movie> movies;

    @Bind(R.id.rVHome)
    RecyclerView rVHome;
    private int page = 1;
    private int finalPage = 1000;
    private String selectSort;

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars(savedInstanceState);
    }

    private void initVars(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList(Movie.MOVIES);
            adapter = new AdapterMovies(movies);
        } else {
            movies = new ArrayList<Movie>();
            adapter = new AdapterMovies(new ArrayList<Movie>());
            loadMovies(getString(R.string.sort_by_popularity));
        }
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
        layoutManager = new GridLayoutManager(getActivity(), getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3);
        rVHome.setHasFixedSize(true);
        rVHome.setLayoutManager(layoutManager);
        rVHome.setAdapter(adapter);
        rVHome.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                page = currentPage;
                if (finalPage >= currentPage) {
                    loadMovies(selectSort);
                }

            }
        });
    }

    private void loadMovies(String sort) {
        selectSort = sort;
        App.getRestClientPublic().getPublicService()
                .getMovies(page,sort)
                .enqueue(new Callback<ResponseMovies>() {
                    @Override
                    public void onResponse(Response<ResponseMovies> response, Retrofit retrofit) {
                        try {
                            movies.addAll(response.body().getResults());
                            adapter.setData(movies);
                            DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);

                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Movie.MOVIES, movies);
        super.onSaveInstanceState(outState);
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
                movies.clear();
                adapter.getItems().clear();
                loadMovies(getString(R.string.sort_by_popularity));
                return true;
            }
            case R.id.action_vote: {
                movies.clear();
                adapter.getItems().clear();
                loadMovies(getString(R.string.sort_by_vote));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
