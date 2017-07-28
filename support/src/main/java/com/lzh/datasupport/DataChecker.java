package com.lzh.datasupport;

import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.tools.Cache;

import java.util.List;

/**
 * The entry class for checker.
 *
 * @author haoge
 */
final class DataChecker {

    static boolean check(Object data) throws Exception{
        List<Mapping> mappings = Cache.findOrCreateMappingList(data.getClass());
        return checkInternal(data, mappings);
    }

    @SuppressWarnings("unchecked")
    private static boolean checkInternal(Object entity, List<Mapping> mappings) {
        boolean success = true;
        try {
            for (Mapping mapping : mappings) {
                ICheck[] checks = mapping.checks;
                for (ICheck check : checks) {
                    if (check == null) {
                        continue;
                    }
                    Object value = mapping.field.get(entity);
                    success = check.check(value, mapping.annotation);
                    if (!success) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            success = false;
        }

        return success;
    }

}
