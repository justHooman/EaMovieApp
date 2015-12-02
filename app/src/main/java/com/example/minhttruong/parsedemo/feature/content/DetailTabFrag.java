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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.model.Movie;

/**
 * Created by minht.truong on 10/30/15.
 */
public class DetailTabFrag extends AbsMovieFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadMovie();
        View v = inflater.inflate(R.layout.tab_layout, container, false);
        TabLayout tl = (TabLayout) v.findViewById(R.id.tab_layout);
        DetailPageAdapter pa = new DetailPageAdapter(getChildFragmentManager());
        ViewPager vp = (ViewPager) v.findViewById(R.id.vp_Content);
        vp.setAdapter(pa);
        tl.setupWithViewPager(vp);
        return v;
    }

    public static final int NUM_OF_TABS = 2;
    public static final int TAB_DETAIL = 0;
    public static final int TAB_REVIEW = 1;

    public class DetailPageAdapter extends FragmentStatePagerAdapter {

        public DetailPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            AbsMovieFrag ret = position == TAB_DETAIL? new DetailFrag() : new ReviewFrag();
            ret.setMovie(mMovie);
            return ret;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == TAB_DETAIL? getString(R.string.detail_title) : getString(R.string.review_title);
        }

        @Override
        public int getCount() {
            return NUM_OF_TABS;
        }
    }
}
