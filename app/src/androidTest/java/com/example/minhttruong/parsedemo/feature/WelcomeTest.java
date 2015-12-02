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

package com.example.minhttruong.parsedemo.feature;

import android.support.v4.app.FragmentManager;

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseTest;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.base.MainAct;
import com.example.minhttruong.parsedemo.feature.user.WelcomeFrag;
import com.example.minhttruong.parsedemo.utils.UiTest;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;

/**
 * Created by minht.truong on 10/26/15.
 */
public class WelcomeTest extends BaseTest {

    @Before
    public void before() {
//        MainAct act = mActRule.getActivity();
//        act.getSupportFragmentManager().popBackStackImmediate(WelcomeFrag.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        act.swapFrag(FragOption.replace(MainAct.MAIN_CONTENT_HOLDER, WelcomeFrag.class));
    }

    @Test
    public void test_Ui() {
        // check display text, only correct if device run in en locate
        UiTest.check_Text_Display(R.id.btn_sign_in, "Sign In");
        UiTest.check_Text_Display(R.id.btn_sign_up, "Sign Up");
        UiTest.check_Text_Display(R.id.btn_skip, "Or Skip");
    }

    @Test
    public void test_SignIn() {
        onView(withId(R.id.btn_switch_to_sign_up)).check(doesNotExist());
        onView(withId(R.id.btn_sign_in)).perform(click());
        checkSignInDisplay();
    }

    @Test
    public void test_SignUp() {
        onView(withId(R.id.btn_switch_to_sign_in)).check(doesNotExist());
        onView(withId(R.id.btn_sign_up)).perform(click());
        checkSignUpDisplay();
    }

    @Test
    public void test_Skip() {
        onView(withId(R.id.btn_skip)).perform(click());
        onView(withId(R.id.btn_skip)).check(doesNotExist());
        checkMainContentScreenDisplay();
    }

}
