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
package com.lzh.datasupport.tools;

import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;
import com.lzh.datasupport.core.model.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class to manage the cache.
 *
 * @author haoge
 */
public final class Cache {

    private final static List EMPTY = Collections.EMPTY_LIST;
    private final static Map<Class, List<Mapping>> RULES_MAPPING = new HashMap<>();
    private final static Map<Class, IMock> MOCKS = new HashMap<>();
    private final static Map<Class, ICheck> CHECKS = new HashMap<>();

    public static List<Mapping> findOrCreateMappingList(Class entity) {
        return findOrCreateMappingList(entity, new ArrayList<Class>());
    }

    static List<Mapping> findOrCreateMappingList(Class entity, ArrayList<Class> cyclic) {
        cyclic.add(entity);
        List<Mapping> list = RULES_MAPPING.get(entity);
        if (list == null) {
            //noinspection unchecked
            RULES_MAPPING.put(entity, EMPTY);
            list = Utils.parse(entity, cyclic);
            RULES_MAPPING.put(entity, list);
        } else if (list == EMPTY) {
            throw new RuntimeException(String.format("Find an unsupported cyclic dependency links %s", cyclic));
        }
        return list;
    }

    public static <T extends IMock> T findOrCreateMocker(Class<T> clz) {
        IMock mock = MOCKS.get(clz);
        if (mock == null) {
            mock = newInstance(clz);
            MOCKS.put(clz, mock);
        }
        //noinspection unchecked
        return (T) mock;
    }

    public static <T extends ICheck> T findOrCreateChecker(Class<T> clz) {
        ICheck check = CHECKS.get(clz);
        if (check == null) {
            check = newInstance(clz);
            CHECKS.put(clz, check);
        }
        //noinspection unchecked
        return (T) check;
    }

    public static ICheck[] findOrCreateChecker(Class[] arr) {
        ICheck[] checks = new ICheck[arr == null ? 0 : arr.length];
        for (int i = 0; i < checks.length; i++) {
            checks[i] = (ICheck) findOrCreateChecker(arr[i]);
        }
        return checks;
    }

    private static <T> T newInstance(Class<T> clz) {
        try {
            return clz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(String.format("The class %s should have an default construction.", clz), e);
        }
    }
}
