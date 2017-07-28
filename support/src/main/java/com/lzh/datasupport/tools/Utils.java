package com.lzh.datasupport.tools;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;
import com.lzh.datasupport.core.model.Mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    private final static String[] SYSTEM_PREFIX = {"java", "android", "javax", "com.android"};

    static List<Mapping> parse(Class entity) {
        // filter system class
        String canonicalName = entity.getCanonicalName();
        for (String prefix : SYSTEM_PREFIX) {
            if (canonicalName.startsWith(prefix)) {
                // if it matched, back an empty list.
                //noinspection unchecked
                return Collections.EMPTY_LIST;
            }
        }

        List<Mapping> list = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            Mapping mapping = findValidator(field);
            if (mapping == null) {
                continue;
            }
            list.add(mapping);
        }
        list.addAll(Cache.findOrCreateMappingList(entity.getSuperclass()));
        return list;
    }

    private static Mapping findValidator(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            Mapping mapping = new Mapping();
            mapping.field = field;
            mapping.annotation = annotation;
            boolean valid = false;
            if (type.isAnnotationPresent(Mocker.class)) {
                valid = true;
                Mocker mocker = type.getAnnotation(Mocker.class);
                Class<? extends IMock> value = mocker.value();
                mapping.mock = Cache.findOrCreateMocker(value);
            }
            if (type.isAnnotationPresent(Checker.class)) {
                valid = true;
                Checker checker = type.getAnnotation(Checker.class);
                Class<? extends ICheck>[] values = checker.value();
                mapping.checks = Cache.findOrCreateChecker(values);
            }

            if (valid) {
                return mapping;
            }
        }
        return null;
    }


}
