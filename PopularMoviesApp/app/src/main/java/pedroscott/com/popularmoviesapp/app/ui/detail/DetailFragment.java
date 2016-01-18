package pedroscott.com.popularmoviesapp.app.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import butterknife.OnClick;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.App;
import pedroscott.com.popularmoviesapp.app.ui.detail.adapter.AdapterDetail;
import pedroscott.com.popularmoviesapp.model.Movie;
import pedroscott.com.popularmoviesapp.model.Review;
import pedroscott.com.popularmoviesapp.model.Trailer;
import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovieReviews;
import pedroscott.com.popularmoviesapp.rest.responses.ResponseMovieTrailers;
import pedroscott.com.popularmoviesapp.utils.DebugUtils;
import pedroscott.com.popularmoviesapp.utils.ShareUtils;
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
public class DetailFragment extends Fragment {

    private static final String TAG = DetailFragment.class.getSimpleName();

    @Bind(R.id.rVDetail)
    RecyclerView rVDetail;

    @Bind(R.id.fBDetailsFavorites)
    FloatingActionButton fBDetailsFavorites;

    @OnClick(R.id.fBDetailsFavorites)
    public void setfavorite(){
        fBDetailsFavorites.setSelected(true);
    }

    private Movie movie;
    private AdapterDetail adapterDetail;
    private LinearLayoutManager layoutManagerDetails;


    /**
     * Create a new Instance of the Fragment with the movie form data.
     */
    public static Fragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Movie.MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
    }

    private void initVars() {
        setHasOptionsMenu(true);
        if (getArguments() != null && getArguments().containsKey(Movie.MOVIE)) {
            movie = getArguments().getParcelable(Movie.MOVIE);
            adapterDetail = new AdapterDetail(movie, new ArrayList<Trailer>(), new ArrayList<Review>());
            getTrailers(movie.getId());
            getReviews(movie.getId());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        intViews();
        return view;
    }

    private void intViews() {
        layoutManagerDetails = new LinearLayoutManager(getActivity());
        rVDetail.setHasFixedSize(true);
        rVDetail.setLayoutManager(layoutManagerDetails);
        rVDetail.setNestedScrollingEnabled(false);
        rVDetail.setAdapter(adapterDetail);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                getActivity().onBackPressed();
                return true;
            }
            case R.id.action_share_trailer:{
                if(adapterDetail.getTrailers().size()>0){
                    ShareUtils.shareString(getActivity(),getString(R.string.url_video_youtube,adapterDetail.getTrailers().get(0).getKey()));
                }else{
                    Snackbar.make(rVDetail,getString(R.string.trailers_entry_title),Snackbar.LENGTH_LONG).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void getReviews(int id) {
        App.getRestClientPublic().getPublicService()
                .getMovieReviews(id)
                .enqueue(new Callback<ResponseMovieReviews>() {
                    @Override
                    public void onResponse(Response<ResponseMovieReviews> response, Retrofit retrofit) {
                        try {
                            adapterDetail.setReviews(response.body().getResults());
                        } catch (NullPointerException e) {

                        }
                        DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);

                    }
                });
    }

    private void getTrailers(int id) {
        App.getRestClientPublic().getPublicService()
                .getMovieTrailers(id)
                .enqueue(new Callback<ResponseMovieTrailers>() {
                    @Override
                    public void onResponse(Response<ResponseMovieTrailers> response, Retrofit retrofit) {
                        try {
                            adapterDetail.setTrailers(response.body().getResults());
                        } catch (NullPointerException e) {

                        }
                        DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);

                    }
                });
    }

    public void scrollToTrailers() {
        if (rVDetail != null) {
            layoutManagerDetails.scrollToPositionWithOffset(1, 0);
        }
    }

    public void scrollToRevies() {
        if (layoutManagerDetails != null) {
            layoutManagerDetails.scrollToPositionWithOffset(2 + adapterDetail.getTrailers().size(), 0);
        }
    }
}
