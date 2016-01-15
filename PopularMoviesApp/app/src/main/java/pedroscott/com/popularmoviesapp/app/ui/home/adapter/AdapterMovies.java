package pedroscott.com.popularmoviesapp.app.ui.home.adapter;

import android.app.Activity;
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
import pedroscott.com.popularmoviesapp.app.ui.MainActivity;
import pedroscott.com.popularmoviesapp.app.ui.base.BaseActivity;
import pedroscott.com.popularmoviesapp.app.ui.detail.DetailActivity;
import pedroscott.com.popularmoviesapp.app.ui.detail.DetailFragment;
import pedroscott.com.popularmoviesapp.model.Movie;

/**
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private static final String TAG = AdapterMovies.class.getSimpleName();
    ArrayList<Movie> items;

    public AdapterMovies(ArrayList<Movie> items) {
        this.items = items;
    }

    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMovies.ViewHolder holder, final int position) {
        Movie movie = items.get(position);
        Glide.clear(holder.iVItemMovie);
        Glide.with(holder.iVItemMovie.getContext())
                .load(holder.iVItemMovie.getContext().getString(R.string.url_images_files) + movie.getPosterPath())
                .fitCenter()
                .centerCrop()
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
                    if (v.getContext() instanceof MainActivity
                            && ((MainActivity) v.getContext()).isScreamWithToPages()) {
                        ((BaseActivity) v.getContext()).navigateDetailContent(
                                DetailFragment.newInstance(movie),
                                v.getContext().getString(R.string.app_name), R.id.container_detail);
                    } else {
                        DetailActivity.newInstance((Activity) iVItemMovie.getContext(), movie);
                    }

                }
            });
        }
    }

}
