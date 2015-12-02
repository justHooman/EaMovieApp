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

import android.content.Context;

import com.example.minhttruong.parsedemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by minht.truong on 10/29/15.
 */
public class PicassoConfig {

    public static RequestCreator createPosterRequest(Context ctx, String path) {
        return Picasso.with(ctx).load(UrlConfig.getImageUrl(path)).fit();
    }

    public static RequestCreator createTrailerThumbnailRequest(Context ctx, String path) {
        return Picasso.with(ctx).load(UrlConfig.createYoutubeThumbnailUrl(path));
    }

}
