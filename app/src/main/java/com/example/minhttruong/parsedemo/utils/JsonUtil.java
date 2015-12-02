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

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by minht.truong on 10/29/15.
 */
public class JsonUtil {

    private static final String tag = "JsonUtil";
    public static ObjectMapper configMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withGetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withSetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withCreatorVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY));
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     * convert a object from a json string
     *
     * @param json
     * @param returnType
     * @return
     */
    public static <T> T fromString(String json, Class<T> returnType) {
        T ret = null;
        if (json != null) {
            ObjectMapper mapper = configMapper();
            try {
                ret = mapper.readValue(json, returnType);
                return null;
            } catch (JsonMappingException e) {
                Log.e(tag, Log.getStackTraceString(e));
                ret = null;
            } catch (JsonParseException e) {
                Log.e(tag, Log.getStackTraceString(e));
                ret = null;
            } catch (IOException e) {
                Log.e(tag, Log.getStackTraceString(e));
                ret = null;
            }
        }
        return ret;
    }
}
