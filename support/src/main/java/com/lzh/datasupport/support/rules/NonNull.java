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
package com.lzh.datasupport.support.rules;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.support.impl.NonNullSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>默认提供的规则注解</p>
 *
 * <p>
 *     <b>此注解可被用于任意数据类型的成员变量之上!</b>
 * </p>
 *
 * <p>
 *     此规则注解只提供了检查器供使用：
 *     <ul>
 *         <li>检查器：对成员变量的数据进行非空判断.</li>
 *     </ul>
 *
 * @see NonNullSupport
 */
@Checker(NonNullSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NonNull {}
