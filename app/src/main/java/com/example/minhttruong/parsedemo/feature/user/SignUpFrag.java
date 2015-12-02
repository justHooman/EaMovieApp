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
import com.example.minhttruong.parsedemo.base.BaseFullScreenFrag;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.base.MainAct;
import com.example.minhttruong.parsedemo.utils.Utils;
import com.example.minhttruong.parsedemo.widget.ProgressFrag;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.lang.ref.WeakReference;

/**
 * Created by minht.truong on 10/26/15.
 */
public class SignUpFrag extends BaseFullScreenFrag implements View.OnClickListener {

    WeakReference<TextView> mTvEmail, mTvPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sign_up_screen, container, false);
        // TODO: 10/26/15
        v.findViewById(R.id.btn_do_sign_up).setOnClickListener(this);
        v.findViewById(R.id.btn_cancel).setOnClickListener(this);
        v.findViewById(R.id.btn_switch_to_sign_in).setOnClickListener(this);
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
            case R.id.btn_switch_to_sign_in:
                swapFrag(FragOption.replace(MainAct.MAIN_CONTENT_HOLDER, SignInFrag.class));
                break;
            case R.id.btn_do_sign_up: {
                // validate input
                if (!Utils.validateEmail(this, Utils.getVal(mTvEmail))) return;

                String username = Utils.getTextViewContent(Utils.getVal(mTvEmail));
                String pass = Utils.getTextViewContent(Utils.getVal(mTvPassword));

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(pass);

                final ProgressFrag dlg = new ProgressFrag();
                String msg = getString(R.string.signing_up);
                dlg.setMsg(msg);
                dlg.show(getChildFragmentManager(), msg);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (getChildFragmentManager() != null) {
                            dlg.dismiss();
                        }
                        if (e == null) {
                            Log.e("TEST", "sucesss");
                            showAlert(getString(R.string.signed_up));
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
