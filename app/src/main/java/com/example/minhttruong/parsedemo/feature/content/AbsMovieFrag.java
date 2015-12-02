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

import android.os.Bundle;

import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.model.Movie;

/**
 * Created by minht.truong on 10/30/15.
 */
public abstract class AbsMovieFrag extends BaseFrag {
    protected Movie mMovie;
    public static final String KEY_MOVIE = "key_movie";

    public void setMovie(Movie movie) {
        mMovie = movie;
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
        getArguments().putParcelable(KEY_MOVIE, movie);
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void loadMovie() {
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(KEY_MOVIE);
        }
    }
}
