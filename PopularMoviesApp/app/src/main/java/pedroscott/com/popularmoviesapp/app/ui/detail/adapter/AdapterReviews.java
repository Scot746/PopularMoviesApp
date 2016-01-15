package pedroscott.com.popularmoviesapp.app.ui.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.ui.home.adapter.AdapterMovies;
import pedroscott.com.popularmoviesapp.model.Review;

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
public class AdapterReviews extends RecyclerView.Adapter<AdapterReviews.ViewHolder> {

    private static final String TAG = AdapterMovies.class.getSimpleName();
    ArrayList<Review> items;

    public AdapterReviews(ArrayList<Review> items) {
        this.items = items;
    }

    @Override
    public AdapterReviews.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterReviews.ViewHolder holder, final int position) {
        Review review = items.get(position);
        holder.tVItemReview.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public void setData(ArrayList<Review> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public ArrayList<Review> getItems() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tVItemReview)
        TextView tVItemReview;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

}
