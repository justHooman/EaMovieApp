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

package com.example.minhttruong.parsedemo.config;

import com.example.minhttruong.parsedemo.model.MovieRRO;
import com.example.minhttruong.parsedemo.model.ReviewRRO;
import com.example.minhttruong.parsedemo.model.TrailerRRO;
import com.example.minhttruong.parsedemo.utils.JsonUtil;

import retrofit.Call;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by minht.truong on 10/27/15.
 */
public class UrlConfig {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static final String API_KEY_PARAM = "api_key=" + UnshareConfig.THE_MOVIEDB_API_KEY;

    private static final String appendMovieDbApi(String path) {
        return new StringBuilder(path).append(API_KEY_PARAM).toString();
    }

    /**
     * link to get popular movies.
     */
    public static final String POPULAR_REQUEST_PATH = "discover/movie?";
//    /**
//     * link to top rated movies path
//     */
//    public static final String TOP_RATED_REQUEST_PATH = "movie/top_rated?";

    /**
     * link to get movies' trailers
     * /movie/{id}/videos
     */
    public static final String TRAILER_REQUEST_PATH = "movie/{id}/videos?";
    /**
     * link to get movies' reviews
     * /movie/{id}/reviews
      */
    public static final String REVIEW_REQUEST_PATH = "movie/{id}/reviews?";


    public interface RetrofitUrl {
        @GET(POPULAR_REQUEST_PATH + API_KEY_PARAM + "&sort_by=popularity.desc")
        Call<MovieRRO> getPoppularMovie();

        @GET(POPULAR_REQUEST_PATH + API_KEY_PARAM + "&sort_by=vote_average.desc")
        Call<MovieRRO> getTopRatedMovies();

        @GET(TRAILER_REQUEST_PATH + API_KEY_PARAM)
        Call<TrailerRRO> getTrailers(@Path("id") long id);

        @GET(REVIEW_REQUEST_PATH + API_KEY_PARAM)
        Call<ReviewRRO> getReviews(@Path("id") long id);
    }

    public static RetrofitUrl getRetrofitService() {
        RetrofitUrl ret = null;
        Retrofit request = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(JsonUtil.configMapper()))
                .build();
        ret = request.create(RetrofitUrl.class);
        return ret;
    }

    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    public static final String BASE_IMAGE_HTTPS_URL = "https://image.tmdb.org/t/p/";

//    public static final String[] BACKDROP_SIZES_PARAMS = {"w300", "w700", "w1280", "original"};
    /**
     * available size of poster;
     */
    public static enum POSTER_SIZE {
        w92(92),
        w154(154),
        w185(185),
        w342(342),
        w500(500),
        w780(780),
        original(900);
        public final int size;

        POSTER_SIZE(int size) {
            this.size = size;
        }

        /**
         * match input size with a proper size to request in server
         * @param imageWidth
         * @return
         */
        public static final POSTER_SIZE matchSize(int imageWidth) {
            POSTER_SIZE ret = original;
            POSTER_SIZE[] values = values();
            if (imageWidth < values[0].size) {
                ret = values[0];
            } else if (imageWidth < values[values.length - 1].size){
                for (int i = 1; i < values.length; i++) {
                    if (imageWidth >= values[i -1].size && imageWidth < values[i].size) {
                        ret = values[i- 1];
                        break;
                    }
                }
            }
            return ret;
        }
    }
//    public static final String[] POSTER_SIZE = {"w92", "w154", "w185", "w342", "w500", "w780", "original"};

    /**
     * base_image_url/ poster size/ poster file;
     */
    public static final String IMAGE_REQUEST_URL = "{posterSize}/{posterPath}";

    private static int sPosterWidth;
    private static int sPosterHeight;

    public static int getPosterWidth() {
        return sPosterWidth;
    }

    public static int getPosterHeight() {
        return sPosterHeight;
    }

    private static POSTER_SIZE sSize;

    /**
     * find and match a size with request image width;
     * @param size
     */
    public static void setImageSize(int size) {
        sPosterWidth = size;
        sPosterHeight = (int) (size * Constant.IMAGE_RATIO);
        sSize = POSTER_SIZE.matchSize(size);
    }

    public static POSTER_SIZE getPosterSize() {
        if (sSize == null) {
            sSize = POSTER_SIZE.values()[0];
        }
        return sSize;
    }

    /**
     * build image request url from image Path
     * @param imagePath
     * @return
     */
    public static final String getImageUrl(String imagePath) {
        return new  StringBuilder(BASE_IMAGE_HTTPS_URL).append(getPosterSize()).append("/")
                .append(imagePath).toString();
    }

    public static final String TRAILER_SITE = "YouTube";
    // link youtube: https://www.youtube.com/watch?v= + trailer key
    public static final String TRAILER_YOUTUBE_LINK = "https://www.youtube.com/watch?v=";

    public static final String createYoutubeThumbnailUrl(String key) {
        return new StringBuilder("http://img.youtube.com/vi/").append(key).append("/0.jpg").toString();
    }

    public static final String buildYoutubeUrl(String key) {
        return new StringBuilder(TRAILER_YOUTUBE_LINK).append(key).toString();
    }
}
