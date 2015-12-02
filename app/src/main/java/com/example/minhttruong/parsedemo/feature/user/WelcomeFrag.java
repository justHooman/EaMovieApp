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

package com.example.minhttruong.parsedemo.feature.user;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.base.BaseFullScreenFrag;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.base.MainAct;
import com.example.minhttruong.parsedemo.feature.content.ContentFrag;

/**
 * Created by minht.truong on 10/26/15.
 */
public class WelcomeFrag extends BaseFullScreenFrag implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.welcome_screen, container, false);
        v.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        v.findViewById(R.id.btn_sign_up).setOnClickListener(this);
        v.findViewById(R.id.btn_skip).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                swapFrag(FragOption.add(MainAct.MAIN_CONTENT_HOLDER, SignInFrag.class));
                break;
            case R.id.btn_sign_up:
                swapFrag(FragOption.add(MainAct.MAIN_CONTENT_HOLDER, SignUpFrag.class));
                break;
            case R.id.btn_skip:
                swapFrag(FragOption.replace(MainAct.MAIN_CONTENT_HOLDER, ContentFrag.class));
        }
    }
}
