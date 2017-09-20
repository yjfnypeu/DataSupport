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
import com.lzh.datasupport.core.support.NonNullSupport;
import com.lzh.datasupport.core.support.RequiresSupport;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>默认提供的规则注解，此注解可被作用于任意类型的成员变量之上</p>
 *
 * <p>
 *     请重视此规则注解！此注解的作用为：对于使用了此注解的成员变量。将会在进行数据模拟或者数据检查时。
 *     触发该变量所代表的数据类型其自身的模拟器、检查器。进行对应操作。可以举个例子来做说明：
 * </p>
 *
 * <pre>
 *     // 对于一个类A：
 *     class A {
 *         // 指定此成员变量将使用其自身的规则进行模拟/检查
 *         &#64;Requires
 *         B b;
 *     }
 *
 *     class B {
 *         &#64;Name
 *         String name;
 *     }
 * </pre>
 *
 * <p>请注意：由于此种规则。有发生循环依赖的危险。所以请在规则定制时. 避免出现循环依赖的情况。如下面这种情况:
 *
 * 将会抛出RuntimeException:Find an unsupported cyclic dependency links ...
 *
 * <pre>
 *     // A 依赖 B中规则
 *     class A {
 *         &#64;Requires
 *         B b;
 *     }
 *
 *     // B 依赖 A中规则。
 *     class B {
 *         &#64;Requires
 *         A a;
 *     }
 * </pre>
 *
 * @see NonNullSupport
 */
@Checker(RequiresSupport.class)
@Mocker(RequiresSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Requires {

    /**
     * 指定是否在进行检查的时候。允许当值为null的时候通过。
     * @return true代表允许此被注解的字段的值为null。
     */
    boolean nullable() default false;
}
