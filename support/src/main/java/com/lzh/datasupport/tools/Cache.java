package com.lzh.datasupport.tools;

import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;
import com.lzh.datasupport.core.model.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Cache {

    private final static Map<Class, List<Mapping>> RULES_MAPPING = new HashMap<>();
    private final static Map<Class, IMock> MOCKS = new HashMap<>();
    private final static Map<Class, ICheck> CHECKS = new HashMap<>();

    public static List<Mapping> findOrCreateMappingList(Class entity) {
        List<Mapping> list = RULES_MAPPING.get(entity);
        if (list == null) {
            list = Utils.parse(entity);
            RULES_MAPPING.put(entity, list);
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
