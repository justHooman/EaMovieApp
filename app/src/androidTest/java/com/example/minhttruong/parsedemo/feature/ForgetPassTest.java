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

import com.example.minhttruong.parsedemo.R;
import com.example.minhttruong.parsedemo.base.BaseTest;
import com.example.minhttruong.parsedemo.base.FragOption;
import com.example.minhttruong.parsedemo.base.MainAct;
import com.example.minhttruong.parsedemo.feature.user.ForgetPassFrag;
import com.example.minhttruong.parsedemo.feature.user.SignInFrag;
import com.example.minhttruong.parsedemo.utils.UiTest;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by minht.truong on 10/26/15.
 */
public class ForgetPassTest extends BaseTest {

    @Before
    public void before() {
        MainAct act = mActRule.getActivity();
        act.swapFrag(FragOption.replace(MainAct.MAIN_CONTENT_HOLDER, ForgetPassFrag.class));
    }

    @Test
    public void testUi() {
        UiTest.check_Text_Display_Contain("reset password");
        UiTest.check_Text_Display(R.id.btn_cancel, "Cancel");
        UiTest.check_Text_Display(R.id.btn_do_reset, "Submit");
    }

    @Test
    public void test_click_in_reset_invalid() {
        onView(withId(R.id.btn_do_reset)).perform(click());
        UiTest.check_Text_Display_Contain("invalid");
    }

    @Test
    public void test_click_in_reset() {
        onView(withId(R.id.input_email)).perform(typeText(VALID_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.btn_do_reset)).perform(click());
        UiTest.check_Text_Display_Contain("Reseting");
    }
}
