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
import com.lzh.datasupport.core.exception.CheckerException;
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

import java.lang.annotation.Annotation;
import java.util.List;

final class DataChecker {

    static boolean check(Object data) throws Exception{
        List<Mapping> mappings = Cache.findOrCreateMappingList(data.getClass());
        return checkInternal(data, mappings);
    }

    @SuppressWarnings("unchecked")
    private static boolean checkInternal(Object entity, List<Mapping> mappings) throws Exception {
        for (Mapping mapping : mappings) {
            ICheck[] checks = mapping.checks;
            for (ICheck check : checks) {
                if (check == null) {
                    continue;
                }
                Object value = mapping.field.get(entity);
                if (!check.check(value, mapping.annotation)) {
                    String msg = String.format("Check for filed [%s] failed by Checker [%s]. and it value is [%s]",
                            mapping.field, check.getClass().getCanonicalName(), value);
                    throw new CheckerException(msg, entity, value, mapping.annotation);
                }
            }
        }
        return true;
    }
}
