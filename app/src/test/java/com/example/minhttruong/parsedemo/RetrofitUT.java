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

package com.example.minhttruong.parsedemo;

import com.example.minhttruong.parsedemo.config.UrlConfig;
import com.example.minhttruong.parsedemo.model.Movie;
import com.example.minhttruong.parsedemo.model.MovieRRO;
import com.example.minhttruong.parsedemo.model.Review;
import com.example.minhttruong.parsedemo.model.ReviewRRO;
import com.example.minhttruong.parsedemo.model.Trailer;
import com.example.minhttruong.parsedemo.model.TrailerRRO;
import com.parse.ParseObject;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

import static junit.framework.Assert.assertTrue;

/**
 * Created by minht.truong on 10/29/15.
 */
public class RetrofitUT {

    @Before
    public void before() {
        ParseObject.registerSubclass(Movie.class);
        ParseObject.registerSubclass(Review.class);
        ParseObject.registerSubclass(Trailer.class);
    }

    @Test
    public void test_Get_Popular_Movies() {
        // TODO: 10/29/15
        Call<MovieRRO> call =  UrlConfig.getRetrofitService().getPoppularMovie();


        Response<MovieRRO> respone = null;
        try {
            respone = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MovieRRO ret = respone == null ? null : respone.body();
        assertTrue(ret != null);
        if (respone != null && respone.isSuccess()) {
            System.out.println("test get PopularMovieRRO ok");
        } else {
            System.out.println("test get PopularMovieRRO fail" + respone == null ? null : respone.message());
        }
    }

    @Test
    public void test_Get_Top_Rated_Movies() {
        // TODO: 10/29/15
        Call<MovieRRO> call =  UrlConfig.getRetrofitService().getTopRatedMovies();


        Response<MovieRRO> respone = null;
        try {
            respone = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MovieRRO ret = respone == null ? null : respone.body();
        assertTrue(ret != null);
        if (respone != null && respone.isSuccess()) {
            System.out.println("test get TopRated ok");
        } else {
            System.out.println("test get TopRated fail" + respone == null ? null : respone.message());
        }
    }

    public static final int MARTIAL_ID = 286217;
    @Test
    public void test_Get_Trailers() {
        // TODO: 10/29/15
        Call<TrailerRRO> call =  UrlConfig.getRetrofitService().getTrailers(MARTIAL_ID);

        Response<TrailerRRO> respone = null;
        try {
            respone = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TrailerRRO ret = respone == null ? null : respone.body();
        assertTrue(ret != null);
        if (respone != null && respone.isSuccess()) {
            System.out.println("test get TrailerRRO ok");
        } else {
            System.out.println("test get TrailerRRO fail" + respone == null ? null : respone.message());
        }
    }

    @Test
    public void test_Get_Reviews() {
        // TODO: 10/29/15
        Call<ReviewRRO> call =  UrlConfig.getRetrofitService().getReviews(MARTIAL_ID);
        Response<ReviewRRO> respone = null;
        try {
            respone = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReviewRRO ret = respone == null ? null : respone.body();
        assertTrue(ret != null);
        if (respone != null && respone.isSuccess()) {
            System.out.println("test get ReviewRRO ok");
        } else {
            System.out.println("test get ReviewRRO fail" + respone == null ? null : respone.message());
        }
    }
}
