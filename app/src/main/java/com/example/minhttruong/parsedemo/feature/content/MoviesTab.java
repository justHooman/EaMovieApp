/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.minhttruong.parsedemo.feature.content;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.config.PicassoConfig;
import com.example.minhttruong.parsedemo.config.UrlConfig;
import com.example.minhttruong.parsedemo.model.Movie;
import com.example.minhttruong.parsedemo.model.MovieRRO;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by minht.truong on 10/29/15.
 */
public class MoviesTab extends BaseFrag implements SwipeRefreshLayout.OnRefreshListener {

    public interface OnMovieClick {
        void onMovieClick(int position, Movie movie);
    }

    protected MovieAdapter mAdapter = new MovieAdapter();
    private WeakReference<SwipeRefreshLayout> mSwipeViewRef;

    private WeakReference<OnMovieClick> mCLickRef;

    public void setCLickRef(WeakReference<OnMovieClick> CLickRef) {
        mCLickRef = CLickRef;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movies_grid, container, false);
        SwipeRefreshLayout srl = (SwipeRefreshLayout) v.findViewById(R.id.refresh_view);
        mSwipeViewRef = Utils.createRef(srl);
        srl.setOnRefreshListener(this);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_content);
        GridLayoutManager glm = new GridLayoutManager(container.getContext(), getResources().getInteger(R.integer.num_of_columns)
                , GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);
        rv.setAdapter(mAdapter);
        mAdapter.setClickRef(mCLickRef);
        getMovies();
        return v;
    }

    @Override
    public void onRefresh() {
        getMovies();
    }

    protected void updateRefreshState(boolean isRefreshing) {
        SwipeRefreshLayout srl = Utils.getVal(mSwipeViewRef);
        if (srl != null) {
            srl.setRefreshing(isRefreshing);
        }
    }

    /**
     * create Retrofit call to request movies list
     * @return
     */
    protected Call<MovieRRO> getMoviesCall() {
        return UrlConfig.getRetrofitService().getPoppularMovie();
    }

    /**
     * get movies data and update refreshing UI
     * default is get Popular Movies
     */
    protected void getMovies() {
        Call<MovieRRO> call = getMoviesCall();
        call.enqueue(new Callback<MovieRRO>() {
            @Override
            public void onResponse(Response<MovieRRO> response, Retrofit retrofit) {
                updateRefreshState(false);
                // TODO: 10/29/15 check fail code
                if (getMainAct() != null && isVisible() && response.isSuccess()) {
                    mAdapter.setItems(response.body().getResults());
                    mAdapter.notifyDataSetChanged();
                }
                Log.d("TEST", "load done");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("TEST", "load fail");
                updateRefreshState(false);
            }
        });
    }

    public static class MostRatedMoviesTab extends MoviesTab {
        @Override
        protected Call<MovieRRO> getMoviesCall() {
            return UrlConfig.getRetrofitService().getTopRatedMovies();
        }
    }

    public static class MyFavMoviesTab extends MoviesTab {
        @Override
        protected void getMovies() {
            // TODO: 10/30/15 get favorite movies
        }
    }

    public static class MovieItemHolder extends RecyclerView.ViewHolder {

        private ImageView mIvPoster;
        private TextView mTvTitle;
        private CheckBox mCbFav;

        public MovieItemHolder(View itemView) {
            super(itemView);
            mIvPoster = (ImageView) itemView.findViewById(R.id.iv_poster);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvName);
            mCbFav = (CheckBox) itemView.findViewById(R.id.cb_fav);
            Utils.updateImageSize(mIvPoster);
        }
    }

    public static class MovieAdapter extends RecyclerView.Adapter<MovieItemHolder> {

        private WeakReference<OnMovieClick> mClickRef;

        public void setClickRef(WeakReference<OnMovieClick> clickRef) {
            mClickRef = clickRef;
        }

        private ArrayList<Movie> mItems;

        public ArrayList<Movie> getItems() {
            return mItems;
        }

        public void setItems(ArrayList<Movie> items) {
            mItems = items;
        }

        @Override
        public MovieItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
            MovieItemHolder ret = new MovieItemHolder(v);
            ItemClickListener icl = new ItemClickListener();
            v.setOnClickListener(icl);
            v.setTag(icl);
            return ret;
        }

        @Override
        public void onBindViewHolder(MovieItemHolder holder, int position) {
            Context ctx = holder.mIvPoster.getContext();
            Picasso.with(ctx).cancelRequest(holder.mIvPoster);
            Movie movie = mItems.get(position);
            ((ItemClickListener) holder.itemView.getTag()).mPosition = position;
            if (movie != null) {
                PicassoConfig.createPosterRequest(ctx, movie.getPoster()).into(holder.mIvPoster);
                holder.mTvTitle.setText(movie.getTitle());
            } else {
                holder.mTvTitle.setText(null);
            }
        }

        protected class ItemClickListener implements View.OnClickListener {

            int mPosition;

            @Override
            public void onClick(View v) {
                if (mItems != null && mItems.size() > mPosition) {
                    Movie movie = mItems.get(mPosition);
                    OnMovieClick omc = Utils.getVal(mClickRef);
                    if (omc != null) {
                        omc.onMovieClick(mPosition, movie);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            return mItems == null ? 0 : mItems.size();
        }
    }
}
