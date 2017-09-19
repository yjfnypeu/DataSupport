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
package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.support.NonNullSupport;
import com.lzh.datasupport.core.support.RangeIntSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * <p>默认提供的规则注解. 请注意此注解只能被使用与数据类型为int的成员变量之上</p>
 *
 * <p>
 *     此规则注解提供了模拟器和检查器以供直接使用：
 *     <ul>
 *         <li>
 *             模拟器：创建一个数据在[min, max]之间的int数据
 *         </li>
 *         <li>
 *             检查器：检查int数据值是否在[min, max]之间。
 *         </li>
 *     </ul>
 *
 * @see NonNullSupport
 */
@Checker(RangeIntSupport.class)
@Mocker(RangeIntSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeInt {
    /**
     * @return The min value.
     */
    int min();

    /**
     * @return The max value.
     */
    int max();
}
