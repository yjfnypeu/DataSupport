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
package com.lzh.datasupport.core.check;

import java.lang.annotation.Annotation;

/**
 * 数据检查器接口。可通过实现此接口来定制各自的检查逻辑。
 * @param <T> 需要被检查的数据类型
 * @param <A> 所使用的规则注解
 *
 * @author haoge
 */
public interface ICheck<T, A extends Annotation> {

    /**
     * 对数据实体类data使用指定规则rule进行数据检查。若检查通过。则返回true
     * @param data 数据实体类
     * @param rule 使用的规则注解
     * @return True代表检查成功
     * @throws Exception 检查中若发生异常。将会被向上抛出
     */
    boolean check(T data, A rule) throws Exception;
}
