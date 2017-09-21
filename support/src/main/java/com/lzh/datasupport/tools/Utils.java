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

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.annotation.Requires;
import com.lzh.datasupport.core.model.Mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    private final static String[] SYSTEM_PREFIX = {"java", "android", "javax", "com.android"};

    static List<Mapping> parse(Class entity, ArrayList<Class> cyclic) {
        // filter system class
        if (isSystemClass(entity)) {
            //noinspection unchecked
            return Collections.EMPTY_LIST;
        }

        List<Mapping> list = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            Mapping mapping = findValidator(field);
            if (mapping == null) {
                continue;
            }
            list.add(mapping);
            if (mapping.annotation.annotationType() == Requires.class) {
                Cache.findOrCreateMappingList(field.getType(), cyclic);
            }
        }
        Class superclass = entity.getSuperclass();
        if (!isSystemClass(superclass)) {
            list.addAll(Cache.findOrCreateMappingList(superclass));
        }
        return list;
    }

    /**
     * 解析出该成员变量的有效数据信息
     *
     * @param field 被解析的成员变量字段
     * @return 解析出的有效信息。或者当此字段没有设置任何的规则时：返回null
     */
    private static Mapping findValidator(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            Mapping mapping = new Mapping();
            mapping.field = field;
            mapping.field.setAccessible(true);
            mapping.annotation = annotation;
            boolean valid = false;

            if (type.isAnnotationPresent(Mocker.class)) {
                valid = true;
                Mocker mocker = type.getAnnotation(Mocker.class);
                mapping.mock = mocker.value();
            }
            if (type.isAnnotationPresent(Checker.class)) {
                valid = true;
                Checker checker = type.getAnnotation(Checker.class);
                mapping.checks = checker.value();
            }

            if (valid) {
                Checker checker = field.getAnnotation(Checker.class);
                Mocker mocker = field.getAnnotation(Mocker.class);
                if (checker != null) {
                    mapping.checks = checker.value();
                }
                if (mocker != null) {
                    mapping.mock = mocker.value();
                }
                return mapping;
            }
        }
        return null;
    }

    private static boolean isSystemClass(Class clz) {
        String canonicalName = clz.getCanonicalName();
        for (String prefix : SYSTEM_PREFIX) {
            if (canonicalName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
