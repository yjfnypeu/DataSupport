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

public interface IMock<T,A extends Annotation> {

    /**
     * To mock an actual data with the rule and the field.
     * @param rule The rule
     * @param field The field that be annotated.
     * @return mock data
     * @throws Exception Any Exception
     */
    T mock(A rule, Field field) throws Exception;
}
