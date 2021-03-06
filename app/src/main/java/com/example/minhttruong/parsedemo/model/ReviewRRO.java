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

package com.example.minhttruong.parsedemo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Review request response object
 * Created by minht.truong on 10/29/15.
 */
public class ReviewRRO {
    @JsonProperty("id")
    private long mId;

    @JsonProperty("results")
    private ArrayList<Review> mResults;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public ArrayList<Review> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<Review> results) {
        mResults = results;
    }
}
