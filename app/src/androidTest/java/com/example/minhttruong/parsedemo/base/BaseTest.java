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

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.utils.UiTest;

import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by minht.truong on 10/26/15.
 */
@RunWith(AndroidJUnit4.class)
public class BaseTest {

    public static final String VALID_EMAIL = "minh.truong@eastagile.com";
    public static final String INVALID_EMAIL = "minh.truong";
    public static final String CORRECT_PASS = "testpass";
    public static final String ERROR_PASS = "test";

    @Rule
    public ActivityTestRule<MainAct> mActRule = new ActivityTestRule<MainAct>(MainAct.class);

    public ViewInteraction checkMainContentScreenDisplay() {
        return onView(withId(R.id.header_container)).check(matches(isDisplayed()));
    }

    public ViewInteraction checkSignUpDisplay() {
        return UiTest.check_Text_Display(R.id.btn_switch_to_sign_in, "Sign In");
    }

    public ViewInteraction checkSignInDisplay() {
        return UiTest.check_Text_Display(R.id.btn_switch_to_sign_up, "Sign Up");
    }

    public ViewInteraction checkForgotPassDisplay() {
        return UiTest.check_Text_Display(R.id.btn_do_reset, "Submit");
    }
}
