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

/**
 * Created by minht.truong on 10/26/15.
 */
public class FragOption {
    public boolean isAdd = true;
    public boolean isAddToBackStack = true;
    public int placeHolderId = 0;
    public String tag;
    public Class<? extends BaseFrag> newFragClazz;

    public FragOption() {
        super();
    }

    public FragOption(int placeHolderId, Class<? extends BaseFrag> fragClazz) {
        this();
        this.placeHolderId = placeHolderId;
        this.newFragClazz = fragClazz;
        this.tag = fragClazz == null ? null : fragClazz.getSimpleName();
    }

    public static FragOption replace(int placeHolderId, Class<? extends BaseFrag> fragClazz) {
        FragOption ret = new FragOption(placeHolderId, fragClazz);
        ret.isAdd = false;
        ret.isAddToBackStack = false;
        return ret;
    }

    /**
     * replace old Frag and add to back stack
     * @param placeHolderId
     * @param fragClazz
     * @return
     */
    public static FragOption add(int placeHolderId, Class<? extends BaseFrag> fragClazz) {
        FragOption ret = new FragOption(placeHolderId, fragClazz);
        ret.isAdd = false;
        ret.isAddToBackStack = true;
        ret.tag = fragClazz.getName();
        return ret;
    }

}
