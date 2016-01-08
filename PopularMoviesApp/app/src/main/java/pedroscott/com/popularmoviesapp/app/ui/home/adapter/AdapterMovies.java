package pedroscott.com.popularmoviesapp.app.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.ui.base.BaseActivity;
import pedroscott.com.popularmoviesapp.app.ui.detail.DetailFragment;
import pedroscott.com.popularmoviesapp.model.Movie;

/**
 * Created by Pedro Scott on 6/2/15.
 */
public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private static final String TAG = AdapterMovies.class.getSimpleName();
    private final Context context;
    ArrayList<Movie> items;

    public AdapterMovies(ArrayList<Movie> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterMovies.ViewHolder holder, final int position) {
        Movie movie = items.get(position);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                .into(holder.iVItemMovie);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public void setData(ArrayList<Movie> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getItems() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iVItemMovie)
        ImageView iVItemMovie;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            iVItemMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie movie = getItems().get(getAdapterPosition());
                    ((BaseActivity) v.getContext()).navigateToLowLevel(
                            DetailFragment.newInstance(movie),
                            v.getContext().getString(R.string.frg_details_title));
                }
            });
        }
    }

}
