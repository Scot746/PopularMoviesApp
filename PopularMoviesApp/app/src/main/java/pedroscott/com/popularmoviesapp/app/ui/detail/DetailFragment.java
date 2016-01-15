package pedroscott.com.popularmoviesapp.app.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.model.Movie;

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

    @Bind(R.id.tVFrgDetailTitleMovie)
    TextView tVFrgDetailTitleMovie;
    @Bind(R.id.iVFrgDetailCover)
    ImageView iVFrgDetailCover;
    @Bind(R.id.tVFrgDetailDate)
    TextView tVFrgDetailDate;
    @Bind(R.id.tVFrgDetailRate)
    TextView tVFrgDetailRate;
    @Bind(R.id.tVFrgDetailSynopsis)
    TextView tVFrgDetailSynopsis;

    @Bind(R.id.pBFrgDetailTrailers)
    ProgressBar pBFrgDetailTrailers;
    @Bind(R.id.pBFrgDetailReviews)
    ProgressBar pBFrgDetailReviews;

    private Movie movie;

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
//            getTrailers(movie.getId());
//            getReviews(movie.getId());

        }
    }

//    private void getReviews(int id) {
//        App.getRestClientPublic().getPublicService()
//                .getMovieReviews(id)
//                .enqueue(new Callback<ResponseMovieReviews>() {
//                    @Override
//                    public void onResponse(Response<ResponseMovieReviews> response, Retrofit retrofit) {
//                        pBFrgDetailReviews.setVisibility(View.GONE);
//                        DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);
//
//                    }
//                });
//    }


//    private void getTrailers(int id) {
//        App.getRestClientPublic().getPublicService()
//                .getMovieTrailers(id)
//                .enqueue(new Callback<ResponseMovieTrailers>() {
//                    @Override
//                    public void onResponse(Response<ResponseMovieTrailers> response, Retrofit retrofit) {
//                        pBFrgDetailTrailers.setVisibility(View.GONE);
//                        DebugUtils.PrintLogMessage(TAG, response.toString(), DebugUtils.DebugMessageType.ERROR);
////
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        DebugUtils.PrintLogMessage(TAG, t.toString(), DebugUtils.DebugMessageType.ERROR);
//
//                    }
//                });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        intViews();
        return view;
    }

    private void intViews() {
        tVFrgDetailTitleMovie.setText(movie.getTitle());
        tVFrgDetailTitleMovie.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(getActivity())
                .load(getString(R.string.url_images_files) + movie.getPosterPath())
                .into(iVFrgDetailCover);
        tVFrgDetailDate.setText(movie.getReleaseDate());
        tVFrgDetailRate.setText(getString(R.string.frg_detail_averages, String.valueOf(movie.getVoteAverage())));
        tVFrgDetailSynopsis.setText(movie.getOverview());
        tVFrgDetailSynopsis.setMovementMethod(new ScrollingMovementMethod());
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
