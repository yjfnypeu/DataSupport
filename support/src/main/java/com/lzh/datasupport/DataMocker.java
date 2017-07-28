package com.lzh.datasupport;

import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

import java.util.List;

/**
 * Created by haoge on 2017/7/28.
 */

final class DataMocker {

    public static <T> T mock(Class<T> clz) throws Exception{
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


}
