package pedroscott.com.popularmoviesapp.app.ui.detail.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.ui.detail.DetailActivity;
import pedroscott.com.popularmoviesapp.app.ui.home.adapter.AdapterMovies;
import pedroscott.com.popularmoviesapp.model.Movie;
import pedroscott.com.popularmoviesapp.model.Review;
import pedroscott.com.popularmoviesapp.model.Trailer;
import pedroscott.com.popularmoviesapp.utils.IntentUtils;

/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>8
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
public class AdapterDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = AdapterMovies.class.getSimpleName();
    private static final int MOVIE_VIEW_TYPE = 100;
    private static final int TRAILERS_HEADER_VIEW_TYPE = 200;
    private static final int TRAILERS_VIEW_TYPE = 300;
    private static final int REVIEWS_HEADER_VIEW_TYPE = 400;
    private static final int REVIEWS_VIEW_TYPE = 500;
    Movie movie;
    ArrayList<Trailer> trailers;
    ArrayList<Review> reviews;

    public AdapterDetail(Movie movie, ArrayList<Trailer> items, ArrayList<Review> reviews) {
        this.trailers = items;
        this.reviews = reviews;
        this.movie = movie;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MOVIE_VIEW_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_movie, parent, false);
                return new ViewHolderMovie(view);
            }
            case TRAILERS_HEADER_VIEW_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headers, parent, false);
                return new ViewHolderHeader(view);
            }
            case TRAILERS_VIEW_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailers, parent, false);
                return new ViewHolderTrailer(view);
            }
            case REVIEWS_HEADER_VIEW_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headers, parent, false);
                return new ViewHolderHeader(view);
            }
            case REVIEWS_VIEW_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews, parent, false);
                return new ViewHolderReview(view);
            }
            default: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headers, parent, false);
                return new ViewHolderHeader(view);
            }
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolderMovie) {
            loadViewHolderMovie((ViewHolderMovie) holder, movie);
        } else if (holder instanceof ViewHolderHeader) {
            loadViewHolderHeader((ViewHolderHeader) holder);
        } else if (holder instanceof ViewHolderTrailer) {
            Trailer trailer = getTrailers().get(position - 2);
            loadViewHolderTrailer((ViewHolderTrailer) holder, trailer);
        } else if (holder instanceof ViewHolderReview) {
            Review review = getReviews().get(position - getTrailers().size() - 3);
            loadViewHolderReview((ViewHolderReview) holder, review);
        }
    }

    private void loadViewHolderReview(ViewHolderReview holder, Review review) {
        holder.tVItemReview.setText(review.getContent());
        holder.tVItemReviewAuthor.setText(review.getAuthor());
    }

    private void loadViewHolderHeader(ViewHolderHeader holder) {
        holder.tVHeader.setText(getItemViewType(holder.getAdapterPosition()) == TRAILERS_HEADER_VIEW_TYPE ? holder.tVHeader.getContext().getString(R.string.trailers_title) :
                holder.tVHeader.getContext().getString(R.string.reviews_title));
        if (getItemViewType(holder.getAdapterPosition()) == TRAILERS_HEADER_VIEW_TYPE && getTrailers().size() == 0) {
            holder.tVItemHeaderMessage.setVisibility(View.VISIBLE);
            holder.tVItemHeaderMessage.setText(holder.tVHeader.getContext().getString(R.string.trailers_entry_title));
        } else if (getItemViewType(holder.getAdapterPosition()) == REVIEWS_HEADER_VIEW_TYPE && getReviews().size() == 0) {
            holder.tVItemHeaderMessage.setVisibility(View.VISIBLE);
            holder.tVItemHeaderMessage.setText(holder.tVHeader.getContext().getString(R.string.reviews_entry_title));
        } else {
            holder.tVItemHeaderMessage.setVisibility(View.GONE);
            holder.tVItemHeaderMessage.setText("");
        }

    }

    private void loadViewHolderMovie(ViewHolderMovie holder, Movie movie) {
        holder.tVFrgDetailTitleMovie.setText(movie.getTitle());
        holder.tVFrgDetailTitleMovie.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(holder.iVFrgDetailCover.getContext())
                .load(holder.iVFrgDetailCover.getContext().getString(R.string.url_images_files) + movie.getPosterPath())
                .into(holder.iVFrgDetailCover);
        holder.tVFrgDetailDate.setText(movie.getReleaseDate());
        holder.tVFrgDetailRate.setText(holder.tVFrgDetailRate.getContext().getString(R.string.frg_detail_averages, String.valueOf(movie.getVoteAverage())));
        holder.tVFrgDetailSynopsis.setText(movie.getOverview());
        holder.tVFrgDetailSynopsis.setMovementMethod(new ScrollingMovementMethod());

        holder.bTFrgDetailGoToTrailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getContext() instanceof DetailActivity) {
                    ((DetailActivity) v.getContext()).scrollToTrailers();
                }
            }
        });
        holder.bTFrgDetailGoToReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getContext() instanceof DetailActivity) {
                    ((DetailActivity) v.getContext()).scrollToReviews();
                }
            }
        });
    }

    private void loadViewHolderTrailer(final ViewHolderTrailer holder, final Trailer trailer) {
        holder.tVItemNameTrailer.setText(trailer.getName());

        holder.bTItemPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.goToYouTube(v.getContext(), trailer.getKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size() + reviews.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MOVIE_VIEW_TYPE;
        } else if (position == 1) {
            return TRAILERS_HEADER_VIEW_TYPE;
        } else if (position >= 2 && position < trailers.size() + 2) {
            return TRAILERS_VIEW_TYPE;
        } else if (position == trailers.size() + 2) {
            return REVIEWS_HEADER_VIEW_TYPE;
        } else if (position > trailers.size() + 2) {
            return REVIEWS_VIEW_TYPE;
        }

        return super.getItemViewType(position);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public class ViewHolderTrailer extends RecyclerView.ViewHolder {
        @Bind(R.id.tVItemNameTrailer)
        TextView tVItemNameTrailer;


        @Bind(R.id.bTItemPlay)
        FloatingActionButton bTItemPlay;

        public ViewHolderTrailer(View v) {
            super(v);
            ButterKnife.bind(this, v);


        }
    }

    public class ViewHolderReview extends RecyclerView.ViewHolder {
        @Bind(R.id.tVItemReview)
        TextView tVItemReview;
        @Bind(R.id.tVItemReviewAuthor)
        TextView tVItemReviewAuthor;

        public ViewHolderReview(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        @Bind(R.id.tVHeader)
        TextView tVHeader;

        @Bind(R.id.tVItemHeaderMessage)
        TextView tVItemHeaderMessage;

        public ViewHolderHeader(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }


    public class ViewHolderMovie extends RecyclerView.ViewHolder {
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
        @Bind(R.id.bTFrgDetailGoToTrailers)
        Button bTFrgDetailGoToTrailers;
        @Bind(R.id.bTFrgDetailGoToReviews)
        Button bTFrgDetailGoToReviews;

        public ViewHolderMovie(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }


}
