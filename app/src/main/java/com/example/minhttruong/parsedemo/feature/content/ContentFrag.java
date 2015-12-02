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
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseActionBarFrag;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.widget.AlertFrag;
import com.parse.ParseUser;

/**
 * Created by minht.truong on 10/26/15.
 */
public class ContentFrag extends BaseActionBarFrag implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_content, container, false);
        // TODO: 10/26/15 code for content
        v.findViewById(R.id.btnUser).setOnClickListener(this);
        swapFrag(getChildFragmentManager(), FragOption.replace(R.id.content_container, MoviesGridFrag.class));
        return v;
    }

    @Override
    protected boolean willHandleBackPress() {
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUser) {
            if (ParseUser.getCurrentUser() != null) {
                ParseUser.logOut();
            }
        }
    }
}
