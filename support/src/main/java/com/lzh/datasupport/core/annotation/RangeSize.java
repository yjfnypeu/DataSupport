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

import com.lzh.datasupport.core.support.RangeSizeSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <i><b>Rule Annotation</b></i>
 *
 * <p>Limit the max size for {@link CharSequence} or {@link java.util.Collection} or {@link java.util.Map}</p>
 *
 * @see RangeSizeSupport
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
