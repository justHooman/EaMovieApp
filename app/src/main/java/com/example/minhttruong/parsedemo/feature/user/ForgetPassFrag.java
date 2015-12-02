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
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFullScreenFrag;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.example.minhttruong.parsedemo.widget.ProgressFrag;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.lang.ref.WeakReference;

/**
 * Created by minht.truong on 10/26/15.
 */
public class ForgetPassFrag extends BaseFullScreenFrag implements View.OnClickListener {
    WeakReference<TextView> mTvEmail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forgot_pass_screen, container, false);

        v.findViewById(R.id.btn_do_reset).setOnClickListener(this);
        v.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mTvEmail = Utils.createRef(((TextView) v.findViewById(R.id.input_email)));
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                backPress();
                break;
            case R.id.btn_do_reset: {
                // validate input
                if (!Utils.validateEmail(this, Utils.getVal(mTvEmail))) return;

                final ProgressFrag processFrag = new ProgressFrag();
                String msg = getString(R.string.reseting_pass);
                processFrag.setMsg(msg);
                processFrag.show(getChildFragmentManager(), msg);
                ParseUser.requestPasswordResetInBackground(Utils.getTextViewContent(Utils.getVal(mTvEmail)), new RequestPasswordResetCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (getChildFragmentManager() != null) {
                            processFrag.dismiss();
                        }
                        if (e == null) {
                            showAlert(getString(R.string.reset_success));
                        } else {
                            showAlert(e.getMessage());
                        }
                    }
                });
            }
                break;
        }
    }
}
