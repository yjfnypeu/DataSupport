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
 * <b>DataSupport</b> 框架入口类。
 *
 * @author haoge
 */
public final class DataSupport {

    // 默认使用的Support实例
    private static DataSupport DEFAULT = null;
    private boolean enableMock = true;
    private boolean enableCheck = true;
    private boolean throwable = false;
    private DataSupport() {}

    /**
     * 获取一个默认的DataSupport实例。此实例全局只有唯一一个。
     * @return 默认创建的实例。
     */
    public static DataSupport getDefault() {
        if (DEFAULT == null) {
            synchronized (DataSupport.class) {
                if (DEFAULT == null) {
                    DEFAULT = new DataSupport();
                }
            }
        }
        return DEFAULT;
    }

    /**
     * 创建一个新的DataSupport实例提供使用。
     * @return DataSupport
     */
    public static DataSupport create() {
        return new DataSupport();
    }

    /**
     * 指定当使异常用{@link #check(Object)}或者{@link #mock(Class)}进行数据处理时。是否在出现时。将异常向外抛出？
     *
     * @param throwable 当设置为TRUE. 则若进行数据处理时出现异常。将自动内部过滤掉。不会将异常抛出而导致crash。
     * @return Itself
     */
    public DataSupport throwable(boolean throwable) {
        this.throwable = throwable;
        return this;
    }

    /**
     * 是否激活检查器。
     *
     * @param enableCheck 是否激活检查器：若指定为true。则使用{@link #check(Object)}进行数据检查时。直接返回true。默认为检查成功。
     * @return Itself
     */
    public DataSupport enableCheck(boolean enableCheck) {
        this.enableCheck = enableCheck;
        return this;
    }

    /**
     * 是否激活模拟器。
     *
     * @param enableMock 是否激活模拟器: 若指定为true, 则使用{@link #mock(Class)} 进行数据模拟时，直接返回null
     * @return Itself
     */
    public DataSupport enableMock(boolean enableMock) {
        this.enableMock = enableMock;
        return this;
    }

    /**
     * 数据检查入口方法，此为功能说明：
     *
     * <p>数据检查将会读取此实体类中所有的(包括其父类)被配置了检查器的成员变量，并分别使用这些检查器对各自的成员变量进行检查。
     *    当所有的检查都通过时。返回true
     *
     * <p>需要注意的几点：
     *     <ul>
     *         <li>
     *             当你在调用此方法前，若通过{@link #enableCheck(boolean)}指定为false时。将不会触发内部检查操作。而直接只进行实体类是否为null的判断
     *         </li>
     *         <li>
     *             当触发检查操作后。若内部有检查操作出现异常。将会根据你之前使用{@link #throwable(boolean)}所指定的是否抛出异常来做过滤。
     *             当指定为false时，则不会抛出异常。
     *         </li>
     *     </ul>
     *
     * @param entity 需要进行检查的实体类。
     * @return True代表检查成功。
     * @see #enableCheck(boolean)
     * @see #throwable(boolean)
     */
    public boolean check(Object entity) {
        try {
            if (enableCheck) {
                return DataChecker.check(entity);
            } else {
                return entity != null;
            }
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException("Check with DataSupport failed:", e);
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * <b>数据模拟</b>入口方法，此为功能说明：
     *
     * <p>
     *     数据模拟将会读取此实体类中所有的(包括其父类)被配置了模拟器的成员变量，并分别使用这些模拟器对各自的成员变量根据其类型进行模拟创建数据。并将创建后的数据赋值给对应的成员变量。
     *     当所有对应的成员变量都被模拟成功后，将被模拟好的数据进行返回。
     *
     * <p>
     *     需要注意的几点：
     *     <ul>
     *         <li>
     *             当你在调用此方法前，若通过{@link #enableMock(boolean)}指定为false时。将不会触发内部模拟操作。而直接返回null。
     *         </li>
     *         <li>
     *             当触发模拟操作后。若内部出现异常。将会根据你之前使用{@link #throwable(boolean)}所指定的是否抛出异常来做过滤。
     *             当指定为false时，则不会抛出异常。
     *         </li>
     *     </ul>
     *
     * @param <T> 被模拟的泛型
     * @param clz 需要进行模拟创建的Class
     * @return 被模拟创建的实例
     * @see #enableMock(boolean)
     * @see #throwable(boolean)
     */
    public <T> T mock(Class<T> clz) {
        try {
            if (enableMock) {
                return DataMocker.mock(clz);
            } else {
                return null;
            }
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException("Mock with DataSupport failed:", e);
            }
            e.printStackTrace();
            return null;
        }
    }


}
