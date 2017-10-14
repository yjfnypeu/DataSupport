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
import com.lzh.datasupport.core.annotation.Target;
import com.lzh.datasupport.core.model.CheckerMapping;
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.core.model.MockerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Utils {

    private final static String[] SYSTEM_PREFIX = {"java", "android", "javax", "com.android"};

    static List<Mapping> parse(Class entity) {
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
//            if (mapping.annotation.annotationType() == Requires.class) {
//                Cache.findOrCreateMappingList(field.getType(), cyclic);
//            }
        }
        Class superclass = entity.getSuperclass();
        if (!isSystemClass(superclass)) {
            list.addAll(Cache.findOrCreateMappingList(superclass));
        }
        return list;
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

    /**
     * 解析出该成员变量的有效数据信息
     *
     * @param field 被解析的成员变量字段
     * @return 解析出的有效信息。或者当此字段没有设置任何的规则时：返回null
     */
    private static Mapping findValidator(Field field) {
        Mapping mapping = new Mapping();
        field.setAccessible(true);
        mapping.field = field;
        mapping.mock = findValidatorMocker(field);
        mapping.checks = findValidatorChecker(field);

        if (mapping.checks.length == 0 && mapping.mock == null) {
            // 当检查器与模拟器均不存在时。代表此字段无对应规则。无效
            return null;
        } else {
            return mapping;
        }
    }

    /**
     * 解析出此成员变量所对应的检查器规则注解信息。
     *
     * @param field 被解析的成员变量字段
     * @return 被解析出的有效信息。若无对应有效信息。将会返回空数组。non-null.
     */
    private static CheckerMapping[] findValidatorChecker(Field field) {
        List<CheckerMapping> mappings = new ArrayList<>();
        List<Annotation> annotations = new ArrayList<>();

        Target target = field.getAnnotation(Target.class);
        if (target != null) {
            Class<? extends Annotation>[] checks = target.checks();
            for (Class<? extends Annotation> check : checks) {
                Annotation annotation = field.getAnnotation(check);
                if (annotation != null
                        && annotation.annotationType().isAnnotationPresent(Checker.class)) {
                    annotations.add(annotation);
                }
            }
        } else {
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                if (annotation.annotationType().isAnnotationPresent(Checker.class)) {
                    annotations.add(annotation);
                }
            }
        }

        for (Annotation annotation : annotations) {
            CheckerMapping mapping = new CheckerMapping();
            mapping.annotation = annotation;
            mapping.checks = annotation.annotationType().getAnnotation(Checker.class).value();
            mappings.add(mapping);
        }
        return mappings.toArray(new CheckerMapping[mappings.size()]);
    }

    /**
     * 解析出此成员变量所对应的模拟器规则注解信息。
     *
     * @param field 被解析的成员变量字段
     * @return 被解析出的有效信息。当无对应的有效mock规则注解时。则返回null
     */
    private static MockerMapping findValidatorMocker(Field field) {
        Target target = field.getAnnotation(Target.class);
        Annotation annotation = null;
        if (target != null) {
            // 若有指定Target注解。则将使用Target所指定的有效规则注解进行使用
            Class<? extends Annotation> mock = target.mock();
            Annotation fieldAnnotation = field.getAnnotation(mock);
            if (fieldAnnotation != null && fieldAnnotation.annotationType().isAnnotationPresent(Mocker.class)) {
                annotation = fieldAnnotation;
            }
        } else {
            // 对于未指定Target注解的，使用最后一次所配置的规则注解进行使用。
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                if (fieldAnnotation.annotationType().isAnnotationPresent(Mocker.class)) {
                    annotation = fieldAnnotation;
                }
            }
        }

        if (annotation == null) {
            return null;
        }
        MockerMapping mapping = new MockerMapping();
        mapping.annotation = annotation;
        mapping.mock = annotation.annotationType().getAnnotation(Mocker.class).value();
        return mapping;
    }
}
