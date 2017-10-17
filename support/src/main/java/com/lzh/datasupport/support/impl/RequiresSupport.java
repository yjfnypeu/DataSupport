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
package com.lzh.datasupport.support.impl;

import com.lzh.datasupport.DataSupport;
import com.lzh.datasupport.support.rules.Requires;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class RequiresSupport implements ICheck<Object, Requires>, IMock<Object, Annotation>{

    // 创建一个此环境默认使用的Support实例。
    private static DataSupport support = DataSupport.create().throwable(true);

    @Override
    public boolean check(Object o, Requires rule) throws Exception {
        if (o == null && rule.nullable()) {
            return true;
        }
        return support.check(o);
    }

    @Override
    public Object mock(Annotation rule, Field field) throws Exception {
        return support.mock(field.getType());
    }
}
