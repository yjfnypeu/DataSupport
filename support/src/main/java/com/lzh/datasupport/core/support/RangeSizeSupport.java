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
package com.lzh.datasupport.core.support;

import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.core.check.ICheck;

import java.util.Collection;
import java.util.Map;

public class RangeSizeSupport implements ICheck<Object, RangeSize>{

    @Override
    public boolean check(Object o, RangeSize rule) throws Exception {
        int size = rule.value();
        if (o == null) {
            return false;
        } else if (o instanceof CharSequence) {
            return size >= ((CharSequence) o).length();
        } else if (o instanceof Collection) {
            return size >= ((Collection) o).size();
        } else if (o instanceof Map) {
            return size >= ((Map) o).size();
        }
        throw new RuntimeException(String.format("Unsupported type: %s", o.getClass()));
    }
}
