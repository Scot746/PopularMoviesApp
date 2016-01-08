package pedroscott.com.popularmoviesapp.app.ui.detail;

import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.model.Movie;


/**
 * A placeholder fragment containing a simple view.
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

    private Movie movie;


    public static Fragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Movie.MOVIE,Parcels.wrap(movie));
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
//        tVFrgDetailTitleMovie.setText(movie.getTitle());
        tVFrgDetailTitleMovie.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(getActivity())
                .load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                .into(iVFrgDetailCover);
//        tVFrgDetailDate.setText(movie.getReleaseDate());
//        tVFrgDetailRate.setText(getString(R.string.frg_detail_averages, String.valueOf(movie.getVoteAverage())));
//        tVFrgDetailSynopsis.setText(movie.getOverview());
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
