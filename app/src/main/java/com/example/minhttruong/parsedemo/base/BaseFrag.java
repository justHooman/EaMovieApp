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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.minhttruong.parsedemo.widget.AlertFrag;

import java.lang.ref.WeakReference;

/**
 * Created by minht.truong on 10/26/15.
 */
public class BaseFrag extends Fragment implements MainAct.OnBackPress {

    protected boolean willHandleBackPress() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (willHandleBackPress()) {
            MainAct act = getMainAct();
            if (act != null) {
                act.setBackPressRef(this);
            }
        }
    }

    @Override
    public void onPause() {
        if (willHandleBackPress()) {
            MainAct act = getMainAct();
            if (act != null) {
                act.setBackPressRef(null);
            }
        }
        super.onPause();
    }

    public MainAct getMainAct() {
        return getActivity() instanceof MainAct ? (MainAct) getActivity() : null;
    }

    public void swapFrag(FragOption fragOpt) {
        MainAct act = getMainAct();
        if (act != null) {
            act.swapFrag(getFragmentManager(), fragOpt);
        }
    }

    public void swapFrag(FragmentManager fm, FragOption fragOpt) {
        MainAct act = getMainAct();
        if (act != null) {
            act.swapFrag(fm, fragOpt);
        }
    }

    public void swapFrag(FragmentManager fm, FragOption fragOpt, BaseFrag newFrag) {
        MainAct act = getMainAct();
        if (act != null) {
            act.swapFrag(fm, fragOpt, newFrag);
        }
    }

    public void backPress() {
        MainAct act = getMainAct();
        if (act != null) {
            act.onBackPressed();
        }
    }

    public void showAlert(String msg) {
        AlertFrag alertFrag = new AlertFrag().initMsg(getActivity(), msg);
        alertFrag.show(getChildFragmentManager(), msg);
    }

    @Override
    public boolean onHandleBackPress() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStack();
            return true;
        }
        return false;
    }
}
