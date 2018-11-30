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

package com.example.minhttruong.parsedemo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.feature.content.ContentFrag;
import com.example.minhttruong.parsedemo.feature.user.WelcomeFrag;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.parse.ParseUser;

import java.lang.ref.WeakReference;

/**
 * Created by minht.truong on 10/26/15.
 */
public class MainAct extends AppCompatActivity {

    private static final String TAG = "MainAct";
    public static final int MAIN_CONTENT_HOLDER = R.id.frag_container;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent();
    }

    private void setContent() {
        setContentView(R.layout.main_act);
//        if (ParseUser.getCurrentUser() == null) {
//            swapFrag(FragOption.replace(MAIN_CONTENT_HOLDER, WelcomeFrag.class));
//        } else {
            swapFrag(FragOption.replace(MAIN_CONTENT_HOLDER, ContentFrag.class));
//        }
    }

    public void swapFrag(FragOption fragOption) {
        swapFrag(getSupportFragmentManager(), fragOption);
    }

    public void swapFrag(FragmentManager fm, FragOption fragOption) {
        swapFrag(fm, fragOption, null);
    }

    /**
     * add or replace new fragment with @FragOption
     * @param fm
     * @param fragOption
     * @param newFrag
     */
    public void swapFrag(FragmentManager fm, FragOption fragOption, BaseFrag newFrag) {
        if (fragOption == null) return;
        if (fragOption.newFragClazz == null) return;
        Fragment frag = getSupportFragmentManager().findFragmentById(fragOption.placeHolderId);
        if (fragOption.newFragClazz.isInstance(frag)) return;
        if (newFrag == null) {
            try {
                newFrag = fragOption.newFragClazz.newInstance();
            } catch (InstantiationException e) {
                newFrag = null;
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (IllegalAccessException e) {
                newFrag = null;
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        if (newFrag != null) {
            FragmentTransaction ft = fm.beginTransaction();
            if (fragOption.isAdd) {
                ft.add(fragOption.placeHolderId, newFrag, fragOption.tag);
            } else {
                ft.replace(fragOption.placeHolderId, newFrag, fragOption.tag);
            }
            if (fragOption.isAddToBackStack) {
                ft.addToBackStack(fragOption.tag);
            }
            ft.commit();
        }
    }

    public interface OnBackPress {
        boolean onHandleBackPress();
    }

    WeakReference<OnBackPress> mBackPressRef;

    public void setBackPressRef(OnBackPress backPress) {
        mBackPressRef = Utils.createRef(backPress);
    }

    @Override
    public void onBackPressed() {
        OnBackPress mListener = Utils.getVal(mBackPressRef);
        if (mListener != null && mListener.onHandleBackPress()) {
            return;
        }
        super.onBackPressed();
    }
}
