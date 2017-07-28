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

import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.reflect.Field;
import java.util.Random;

public class RangeIntSupport implements ICheck<Integer, RangeInt>, IMock<Integer, RangeInt>{

    private Random random = new Random();

    @Override
    public Integer mock(RangeInt rule, Field field) throws Exception {
        int min = rule.min();
        int max = rule.max();
        return random.nextInt(max) - min;
    }

    @Override
    public boolean check(Integer integer, RangeInt rule) throws Exception {
        int min = rule.min();
        int max = rule.max();
        return integer != null && integer > min && integer < max;
    }
}
