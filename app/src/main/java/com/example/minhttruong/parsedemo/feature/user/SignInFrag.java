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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.base.BaseFullScreenFrag;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.base.MainAct;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.example.minhttruong.parsedemo.widget.ProgressFrag;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.lang.ref.WeakReference;

/**
 * Created by minht.truong on 10/26/15.
 */
public class SignInFrag extends BaseFullScreenFrag implements View.OnClickListener {

    WeakReference<TextView> mTvEmail, mTvPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sign_in_screen, container, false);

        v.findViewById(R.id.btn_do_sign_in).setOnClickListener(this);
        v.findViewById(R.id.btn_cancel).setOnClickListener(this);
        v.findViewById(R.id.btn_switch_to_sign_up).setOnClickListener(this);
        v.findViewById(R.id.btn_forgot_pass).setOnClickListener(this);
        mTvEmail = Utils.createRef(((TextView) v.findViewById(R.id.input_email)));
        mTvPassword = Utils.createRef(((TextView) v.findViewById(R.id.input_pass)));
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_cancel:
                backPress();
                break;
            case R.id.btn_switch_to_sign_up:
                swapFrag(FragOption.replace(MainAct.MAIN_CONTENT_HOLDER, SignUpFrag.class));
                break;
            case R.id.btn_forgot_pass:
                swapFrag(FragOption.add(MainAct.MAIN_CONTENT_HOLDER, ForgetPassFrag.class));
                break;
            case R.id.btn_do_sign_in: {
                // validate input
                if (!Utils.validateEmail(this, Utils.getVal(mTvEmail))) return;
                String user = Utils.getTextViewContent(Utils.getVal(mTvEmail));
                String pass = Utils.getTextViewContent(Utils.getVal(mTvPassword));

                final ProgressFrag dlg = new ProgressFrag();
                String msg = getString(R.string.signing_in);
                dlg.setMsg(msg);
                dlg.show(getChildFragmentManager(), msg);
                ParseUser.logInInBackground(user, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (getChildFragmentManager() != null) {
                            dlg.dismiss();
                        }
                        if (user != null) {
                            Log.e("TEST", "Log success");
                            showAlert(getString(R.string.signed_in));
                        } else {
                            Log.e("TEST", e.toString());
                            showAlert(e.getMessage());
                        }
                    }
                });
            }
            break;

        }
    }
}
