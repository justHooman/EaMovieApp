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

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.minhttruong.parsedemo.config.Constant;
import com.example.minhttruong.parsedemo.config.UrlConfig;
import com.example.minhttruong.parsedemo.model.Movie;
import com.example.minhttruong.parsedemo.model.Review;
import com.example.minhttruong.parsedemo.model.Trailer;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import retrofit.http.Url;

/**
 * Created by minht.truong on 10/26/15.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: 10/30/15 calculate and set image witdh
        DisplayMetrics dm = getResources().getDisplayMetrics();
        UrlConfig.setImageSize(dm.widthPixels / getResources().getInteger(R.integer.num_of_columns) -
                2 * getResources().getDimensionPixelSize(R.dimen.btn_space));

        ParseObject.registerSubclass(Movie.class);
        ParseObject.registerSubclass(Review.class);
        ParseObject.registerSubclass(Trailer.class);
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, Constant.PARSE_APP_ID, Constant.PARSE_CLIENT_KEY);
//        ParseUser.enableAutomaticUser();
    }
}
