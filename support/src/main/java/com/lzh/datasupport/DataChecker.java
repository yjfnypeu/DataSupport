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
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

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
            if (mapping == null) {
                continue;
            }

            Class<? extends ICheck>[] checks = mapping.checks;
            for (int j = 0; j < (checks == null ? 0 : checks.length); j++) {
                Class<? extends ICheck> check = checks[j];
                if (check == null) {
                    continue;
                }

                Object value = mapping.field.get(entity);
                if (!Cache.findOrCreateChecker(check).check(value, mapping.annotation)) {
                    throw new RuntimeException(String.format("Check failed for Field:[%s]: checker => %s & value => %s", mapping.field, check, value));
                }
            }
        }
        return true;
    }

}
