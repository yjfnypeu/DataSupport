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

import com.lzh.datasupport.core.check.ICheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>此注解用于配置<b>检查器{@link ICheck}</b>提供使用，是框架内部的<b>基础规则注解</b></p>
 *
 * <p>
 *     此检查器配置注解。有两种配置使用方式：
 * </p>
 *
 * <p>
 *     1. 作用于定制的规则注解之上，如{@link RangeInt}, 作用为：对于使用了此注解的成员变量。将会使用此注解上所配置的检查器进行规则检查：
 * </p>
 *
 * <pre class="prettyprint">
 *     class User {
 *         // 代表此成员变量将使用RangeInt所提供的检查器对name变量的数据进行检查。
 *         &#64RangeInt(min = 1, max = 11);
 *         String name;
 *     }
 * </pre>
 *
 * <p>
 *     2.或者直接作用于添加了任意注解的成员变量之上，代表此成员变量将直接使用此处所单独配置的检查器进行数据检查操作。若添加的注解自身有配置检查器时，将会被替换掉。
 * </p>
 *
 * <pre>
 *     class User {
 *         // 表示此成员变量将使用此处所特别重置过的检查器对name变量进行数据检查
 *         &#64Checker(NameChecker.class)
 *         &#64RangeInt(min = 1, max = 11);
 *         String name;
 *     }
 * </pre>
 *
 * <p>
 *     若对于一个变量。所使用的注解。并未通过以上两种方式的任意一种所进行检查器配置。则代表此变量将不存在有对应的检查器。在进行数据检查时将被pass
 * </p>
 *
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checker {
    /**
     * @return 所有配置的模拟器Class
     */
    Class<? extends ICheck>[] value();
}
