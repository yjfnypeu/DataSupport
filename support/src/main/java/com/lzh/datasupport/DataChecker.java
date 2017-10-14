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

import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.model.CheckerMapping;
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

import java.lang.annotation.Annotation;
import java.util.List;

final class DataChecker {

    static boolean check(Object data) throws Exception {
        List<Mapping> mappings = Cache.findOrCreateMappingList(data.getClass());
        return checkInternal(data, mappings);
    }

    @SuppressWarnings("unchecked")
    private static boolean checkInternal(Object entity, List<Mapping> mappings) throws Exception {
        for (int i = 0; i < (mappings == null ? 0 : mappings.size()); i++) {
            Mapping mapping = mappings.get(i);
            if (mapping == null || mapping.checks == null) {
                continue;
            }

            try {
                flatCheckMappings(mapping.field.get(entity), mapping.checks);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Check for class [%s] with it field member [%s] failed",
                        mapping.field.getDeclaringClass(), mapping.field.getName()), e);
            }
        }
        return true;
    }

    private static void flatCheckMappings(Object value, CheckerMapping[] checks) throws Exception {
        for (CheckerMapping mapping : checks) {
            if (mapping == null) {
                continue;
            }

            flatCheckMapping(value, mapping);
        }
    }

    private static void flatCheckMapping(Object value, CheckerMapping mapping) throws Exception {
        ICheck[] checks = Cache.findOrCreateChecker(mapping.checks);
        Annotation annotation = mapping.annotation;

        for (ICheck check : checks) {
            //noinspection unchecked
            if (!check.check(value, annotation)) {
                throw new RuntimeException(String.format("Check by %s with value %s", check.getClass(), value));
            }
        }
    }

}
