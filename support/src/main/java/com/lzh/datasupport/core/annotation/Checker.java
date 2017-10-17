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
import com.lzh.datasupport.support.rules.RangeInt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>此注解用于配置<b>检查器{@link ICheck}</b>提供使用，是框架内部的<b>基础规则注解</b>
 *
 * <p>被此注解所注释的规则注解。被称为 <i><b>检查器规则注解</b></i>
 *
 * <p>
 *     使用方式：作用于定制的规则注解之上，如{@link RangeInt}, 作用为：对于使用了此注解的成员变量。将会使用此注解上所配置的检查器进行规则检查。
 *     而{@link RangeInt}这种被注释的注解，又被称为 <i><b>检查器规则注解</b></i>。
 * </p>
 *
 * <pre class="prettyprint">
 *     // 示例代码
 *     class User {
 *         // 代表此成员变量将使用RangeInt所提供的检查器对name变量的数据进行检查。
 *         &#64;ChineseName
 *         String name;
 *     }
 * </pre>
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Checker {
    /**
     * @return 所有配置的模拟器Class
     */
    Class<? extends ICheck>[] value();
}
