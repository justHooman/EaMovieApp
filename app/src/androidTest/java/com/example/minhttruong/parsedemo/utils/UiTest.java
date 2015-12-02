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

import android.support.test.espresso.ViewInteraction;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by minht.truong on 10/26/15.
 */
public class UiTest {
    /**
     * ultility to find and check a text view is displayed with a text content
     * @param viewId
     * @param content
     * @return
     */
    public static final ViewInteraction check_Text_Display(int viewId, String content) {
        return onView(withId(viewId)).check(matches(isAssignableFrom(TextView.class)))
                .check(matches(isDisplayed()))
                .check(matches(withText(content)));
    }

    public static final ViewInteraction check_Text_Display_Contain(String subString) {
        return onView(allOf(withText(containsString(subString)))).check(matches(isAssignableFrom(TextView.class)))
                .check(matches(isDisplayed()));
    }

    /**
     * utitlity for the text display with correct hint
     * @param viewId
     * @param hint
     * @return
     */
    public static final ViewInteraction check_Text_Hint(int viewId, String hint) {
        return onView(withId(viewId)).check(matches(isAssignableFrom(TextView.class)))
                .check(matches(isDisplayed()))
                .check(matches(HintMatcher.withHint(hint)));
    }

}
