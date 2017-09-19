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
package com.lzh.datasupport.core.mock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 数据模拟器接口。可通过实现此接口来定制各自的数据模拟逻辑。
 * @param <T> 需要被模拟的数据类型
 * @param <A> 所使用的规则注解
 *
 * @author haoge
 */
public interface IMock<T, A extends Annotation> {

    /**
     * 对指定成员变量字段field使用规则注解rule进行数据模拟创建，并将模拟后的数据返回。由框架将其设置到对应的field之上！
     * @param rule 使用的规则注解
     * @param field 被该规则注解rule所注释了的成员变量字段。
     * @return 被模拟创建的数据
     * @throws Exception Any Exception
     */
    T mock(A rule, Field field) throws Exception;
}
