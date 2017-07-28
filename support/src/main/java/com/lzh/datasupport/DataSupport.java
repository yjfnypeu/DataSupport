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
package com.lzh.datasupport;

/**
 * The entry class of DataSupport
 * Created by haoge on 2017/7/27.
 */
public final class DataSupport {

    private boolean throwable = true;
    private DataSupport() {}
    public static DataSupport create() {
        return new DataSupport();
    }

    /**
     * Set a flag to indicate whether should be thrown when causing an exception.
     * @param throwable True to ignore exception.
     * @return Itself
     */
    public DataSupport throwable(boolean throwable) {
        this.throwable = throwable;
        return this;
    }

    /**
     * The entry method of check.
     * @param entity The entity to be checked
     * @return True if check successfully.
     */
    public boolean check(Object entity) {
        try {
            return DataChecker.check(entity);
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException("DataSupport checked failed", e);
            }
            return false;
        }
    }

    /**
     * The entry method of mock.
     * @param clz The class to be mock.
     * @param <T> The generic type.
     * @return The new instance create by mocker or null.
     */
    public <T> T mock(Class<T> clz) {
        try {
            return DataMocker.mock(clz);
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException("DataSupport mocked failed", e);
            }
            return null;
        }
    }

}
