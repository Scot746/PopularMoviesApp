package pedroscott.com.popularmoviesapp.app.ui.detail.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.ui.home.adapter.AdapterMovies;
import pedroscott.com.popularmoviesapp.model.Review;
import pedroscott.com.popularmoviesapp.model.Trailer;
import pedroscott.com.popularmoviesapp.utils.IntentUtils;

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
public class AdapterTrailers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = AdapterMovies.class.getSimpleName();
    ArrayList<Trailer> trailers;
    ArrayList<Review> reviews;

    public AdapterTrailers(ArrayList<Trailer> items) {
        this.trailers = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailers, parent, false);
        return new ViewHolderTrailer(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Trailer trailer = trailers.get(position);
        if(holder instanceof ViewHolderTrailer){
            loadViewHolderTrailer((ViewHolderTrailer) holder,trailer);
        }

    }

    private void loadViewHolderTrailer(ViewHolderTrailer holder,Trailer trailer) {
        holder.tVItemNameTrailer.setText(trailer.getName());
        Glide.with(holder.iVItemImageTrailer.getContext())
                .load(holder.iVItemImageTrailer.getContext().getString(R.string.url_images_youtube, trailer.getKey()))
                .fitCenter()
                .into(holder.iVItemImageTrailer);
    }

    @Override
    public int getItemCount() {
        if (trailers != null) {
            return trailers.size();
        }
        return 0;
    }

    public void setData(ArrayList<Trailer> data) {
        this.trailers = data;
        notifyDataSetChanged();
    }

    public ArrayList<Trailer> getItems() {
        return trailers;
    }

    public class ViewHolderTrailer extends RecyclerView.ViewHolder {
        @Bind(R.id.tVItemNameTrailer)
        TextView tVItemNameTrailer;

        @Bind(R.id.iVItemImageTrailer)
        ImageView iVItemImageTrailer;

        @Bind(R.id.bTItemPlay)
        FloatingActionButton bTItemPlay;

        public ViewHolderTrailer(View v) {
            super(v);
            ButterKnife.bind(this, v);
            bTItemPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.goToYooTube(v.getContext(), getItems().get(getAdapterPosition()).getKey());
                }
            });

        }
    }

    public class ViewHolderReview extends RecyclerView.ViewHolder {

        @Bind(R.id.tVItemReview)
        TextView tVItemReview;

        public ViewHolderReview(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }


}
