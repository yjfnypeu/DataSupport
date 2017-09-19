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
import com.lzh.datasupport.core.support.RangeSizeSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

/**
 * <p>默认提供的规则注解, 请注意此注解只能被使用于数据类型为[{@link CharSequence}, {@link java.util.Map}, {@link java.util.Collection}]的成员变量之上</p>
 *
 * <p>
 *     此规则注解只提供了检查器进行使用：
 *     <ul>
 *         <li>
 *             检查器: 检查数据的值的长度是否在指定范围之内:<br>
 *             1. {@link CharSequence}判断其{@link CharSequence#length()}<br>
 *             2. {@link Map}判断其{@link Map#size()}<br>
 *             3. {@link List}判断其{@link List#size()}
 *         </li>
 *     </ul>
 * </p>
 *
 * @see NonNullSupport
 */
@Checker(RangeSizeSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeSize {
    /**
     * it should greater than or equal to 0.
     * @return The max size for this field. include itself
     */
    int value() default 0;
}
