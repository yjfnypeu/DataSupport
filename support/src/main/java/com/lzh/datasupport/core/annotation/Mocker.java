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

import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>This annotation is used for set the checker class for <i><b>Rule Annotation</b></i>
 *
 * <p>
 *     The scope of this annotation is to a Rule Annotations or to the field that be annotated by a Rule Annotation.
 *     When it be annotated by a Rule Annotation. it means that the Rule Annotation will use the Mocker that you defined.
 *     Otherwise. When it be annotated by a field that is annotated by a Rule Annotation.
 *     it means that the old Mocker be defined by the Rule Annotation will be replaced to the new Mocker you defined
 * </p>
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mocker {
    /**
     * @return The mocker data.
     */
    Class<? extends IMock> value();
}
