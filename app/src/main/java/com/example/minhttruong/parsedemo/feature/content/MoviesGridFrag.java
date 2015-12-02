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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.model.Movie;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.squareup.okhttp.internal.Util;

/**
 * Created by minht.truong on 10/29/15.
 */
public class MoviesGridFrag extends BaseFrag implements MoviesTab.OnMovieClick {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_layout, container, false);
        TabLayout tl = (TabLayout) v.findViewById(R.id.tab_layout);
        MoviePageAdapter pa = new MoviePageAdapter(getChildFragmentManager());
        ViewPager vp = (ViewPager) v.findViewById(R.id.vp_Content);
        vp.setAdapter(pa);
        tl.setupWithViewPager(vp);
        return v;
    }

    @Override
    public void onMovieClick(int position, Movie movie) {
        DetailTabFrag frag = new DetailTabFrag();
        frag.setMovie(movie);
        swapFrag(getFragmentManager(), FragOption.add(R.id.content_container, DetailTabFrag.class), frag);
    }

    public static final int NUM_OF_TABS = 3;
    public static final int TAB_POPULAR = 0;
    public static final int TAB_MOST_RATED = 1;
    public static final int TAB_MY_FAV = 2;

    private class MoviePageAdapter extends FragmentStatePagerAdapter {

        public MoviePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MoviesTab ret = null;
            switch (position) {
                case TAB_POPULAR:
                    ret = new MoviesTab();
                    break;
                case TAB_MOST_RATED:
                    ret = new MoviesTab.MostRatedMoviesTab();
                    break;
                case TAB_MY_FAV:
                    ret = new MoviesTab.MyFavMoviesTab();
                    break;
            }
            if (ret != null) {
                ret.setCLickRef(Utils.<MoviesTab.OnMovieClick>createRef(MoviesGridFrag.this));
            }
            return ret;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int[] tabs_titles_res = {R.string.popular_title, R.string.most_rated_title, R.string.my_fav_title};
            return getText(tabs_titles_res[position]);
        }

        @Override
        public int getCount() {
            return NUM_OF_TABS;
        }
    }
}
