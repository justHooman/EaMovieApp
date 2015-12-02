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

package com.example.minhttruong.parsedemo.utils;

import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseFrag;
import com.example.minhttruong.parsedemo.config.Constant;
import com.example.minhttruong.parsedemo.config.UrlConfig;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

/**
 * Created by minht.truong on 10/26/15.
 */
public class Utils {
    /**
     * get the object which ref is holding.
     * @param ref
     * @param <T>
     * @return
     */
    public static final <T> T getVal(WeakReference<T> ref) {
        return ref == null ? null : ref.get();
    }

    /**
     * create new WeakReference for a object.
     * @param val
     * @param <T>
     * @return
     */
    public static final <T> WeakReference<T> createRef(T val) {
        return new WeakReference<T>(val);
    }

    /**
     * get text content of
     * @param tv
     * @return
     */
    public static final String getTextViewContent(TextView tv) {
        return tv == null ? Constant.EMPTY_STRING : tv.getText().toString();
    }

    public static final void setText(View tv, CharSequence cs) {
        if (tv instanceof TextView) {
            ((TextView) tv).setText(cs);
        }
    }

    public static void updateImageSize(ImageView iv) {
        ViewGroup.LayoutParams params = iv == null? null : iv.getLayoutParams();
        if (params == null) return;
        params.width = UrlConfig.getPosterWidth();
        params.height = UrlConfig.getPosterHeight();
        iv.setLayoutParams(params);
    }

    /**
     * validate email text field and show error alert in fail case
     * @param frag
     * @param tvEmail
     * @return
     */
    public static boolean validateEmail(BaseFrag frag, TextView tvEmail) {
        boolean ret = false;
        if (frag == null || tvEmail == null) return ret;
        ret = Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText()).matches();
        if (!ret) {
            frag.showAlert(frag.getString(R.string.email_invalid));
        }
        return ret;
    }
}
