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

import com.lzh.datasupport.core.check.ICheck;

import java.lang.annotation.Annotation;

public class NonNullSupport implements ICheck<Object, Annotation>{

    @Override
    public boolean check(Object o, Annotation rule) throws Exception {
        if (o == null) {
            return false;
        } else if (o instanceof CharSequence) {
            return ((CharSequence) o).length() != 0;
        } else {
            return true;
        }
    }
}