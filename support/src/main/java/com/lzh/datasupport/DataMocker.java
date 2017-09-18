/*
 * Copyright (C) 2017 Haoge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzh.datasupport;

import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

import java.util.List;

final class DataMocker {

    private static boolean ENABLE = true;

    public static <T> T mock(Class<T> clz) throws Exception{
        if (!ENABLE) {
            return null;
        }
        List<Mapping> mappings = Cache.findOrCreateMappingList(clz);
        return mockInternal(clz, mappings);
    }

    @SuppressWarnings("unchecked")
    private static <T> T mockInternal(Class<T> clz, List<Mapping> mappings) throws Exception{
        T mock = clz.newInstance();
        for (Mapping mapping : mappings) {
            if (mapping.mock == null) {
                continue;
            }
            Object subValue = mapping.mock.mock(mapping.annotation, mapping.field);
            mapping.field.set(mock, subValue);
        }

        return mock;
    }

    /**
     * Whether or not to enable the mocker. if not enabled, the mock result should always returns null.
     * @param enable True for enable
     */
    public static void enable(boolean enable) {
        ENABLE = enable;
    }
}
