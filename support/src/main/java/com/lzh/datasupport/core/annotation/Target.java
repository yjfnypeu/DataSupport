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

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于指定模拟器与检查器所使用的规则注解，当一个字段需要同时配置使用<i>多个规则注解</i>的时候。可通过注解明确指定使用的模拟注解与检查注解
 *
 * <p>当对应的字段添加了此注解后。则将使用此注解中所指定的规则注解进行使用:
 *
 * <pre>
 * public class Entity {
 *     // 当一个字段含有多个规则注解时。可使用Target进行明确指定。
 *     // 若不使用Target进行明确指定：则检查规则将根据注解的顺序依次触发。
 *     // 模拟规则会使用最后一次所使用的模拟注解
 *     // 模拟注解为被Mocker所标注的注解，检查注解为被Checker所标注的注解。
 *     &#64;Target(mock=Rule1.class, checks={Rule1.class, Rule2.class})
 *     &#64;Rule1
 *     &#64;Rule2
 *     String field;
 * }
 *
 * </pre>
 *
 * @author haoge on 2017/10/12.
 */
@java.lang.annotation.Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Target {
    Class<? extends Annotation> mock() default Annotation.class;
    Class<? extends Annotation>[] checks() default {};
}
