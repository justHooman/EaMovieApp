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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.config.PicassoConfig;
import com.example.minhttruong.parsedemo.config.UrlConfig;
import com.example.minhttruong.parsedemo.model.Trailer;
import com.example.minhttruong.parsedemo.model.TrailerRRO;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.google.android.youtube.player.YouTubeIntents;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by minht.truong on 10/30/15.
 */
public class DetailFrag extends AbsMovieFrag {

    private TrailerAdapter mAdapter = new TrailerAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadMovie();
        View v = inflater.inflate(R.layout.detail_frag, container, false);
        if (mMovie != null) {
            ImageView ivPoster = (ImageView) v.findViewById(R.id.iv_poster);
            Utils.updateImageSize(ivPoster);
            PicassoConfig.createPosterRequest(mMovie.getPoster()).into(ivPoster);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rbRate);
            rb.setRating(mMovie.getRating());
            Utils.setText(v.findViewById(R.id.tvName), mMovie.getTitle());
            Utils.setText(v.findViewById(R.id.tvRealeaseDate), mMovie.getRelatedDate());
            Utils.setText(v.findViewById(R.id.tvOverview), mMovie.getOverview());
            RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvTraileres);
            GridLayoutManager glm = new GridLayoutManager(container.getContext(), getResources().getInteger(R.integer.num_of_columns)
                    , GridLayoutManager.VERTICAL, false);
            rv.setLayoutManager(glm);
            rv.setAdapter(mAdapter);
        }
        getTrailers();
        // TODO: 10/30/15 request trailer

        return v;
    }

    private void getTrailers() {
        if (mMovie == null) return;
        Call<TrailerRRO> call = UrlConfig.getRetrofitService().getTrailers(mMovie.getId());
        call.enqueue(new Callback<TrailerRRO>() {
            @Override
            public void onResponse(Call<TrailerRRO> call, Response<TrailerRRO> response) {
                if (response != null && response.isSuccessful()) {
                    TrailerRRO res = response.body();
                    if (res != null) {
                        mAdapter.setItems(res.getResults());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerRRO> call, Throwable t) {
                Log.d("TEST", "request trailer fail");
                Log.d("TEST", Log.getStackTraceString(t));
            }
        });
    }

    public static class TrailerHolder extends RecyclerView.ViewHolder {

        private ImageView mIvThumbnail;
        private TextView mTvName;

        public TrailerHolder(View itemView) {
            super(itemView);
            mIvThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
            mTvName = (TextView) itemView.findViewById(R.id.tvTrailerName);
        }
    }

    public class TrailerAdapter extends RecyclerView.Adapter<TrailerHolder> implements View.OnClickListener {

        private ArrayList<Trailer> mItems;

        public void setItems(ArrayList<Trailer> items) {
            mItems = items;
        }

        @Override
        public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
            v.setOnClickListener(this);
            return new TrailerHolder(v);
        }

        @Override
        public void onBindViewHolder(TrailerHolder holder, int position) {
            Trailer trailer = mItems.get(position);
            Picasso.get().cancelRequest(holder.mIvThumbnail);
            holder.itemView.setTag(position);
            if (trailer != null) {
                PicassoConfig.createTrailerThumbnailRequest(trailer.getKey()).into(holder.mIvThumbnail);
                holder.mTvName.setText(trailer.getName());
            }
        }

        @Override
        public int getItemCount() {
            return mItems == null ? 0: mItems.size();
        }

        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            Trailer trailer = mItems.get(pos);
            Context ctx = v.getContext();
            if (YouTubeIntents.isYouTubeInstalled(ctx)) {
                if (YouTubeIntents.canResolvePlayVideoIntent(ctx)) {
                    Intent intent;
                    if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(ctx)) {
                        intent =
                        YouTubeIntents.createPlayVideoIntentWithOptions(ctx, trailer.getKey(), true, true);
                    } else {
                        intent = YouTubeIntents.createPlayVideoIntent(ctx, trailer.getKey());
                    }
                    ctx.startActivity(intent);
                    return;
                }
            }
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW);
            youtubeIntent.setData(Uri.parse(UrlConfig.buildYoutubeUrl(trailer.getKey())));
            ctx.startActivity(youtubeIntent);
        }
    }
}
