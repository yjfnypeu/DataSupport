package com.lzh.datasupport.pojo;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.annotation.Target;
import com.lzh.datasupport.support.ChineseName;
import com.lzh.datasupport.support.rules.Requires;

/**
 * 此实体类用于明确的描述各种规则配置方式
 * @author haoge on 2017/10/16.
 */
public class Entity {
    /**
     * 1. 单一规则：直接使用对应注解中所使用的规则
     */
    @ChineseName
    public String single;

    /**
     * 2. 同时添加多条注解规则：(未使用{@link Target}做特殊指定)
     *
     * checker: 所有含有{@link Checker}的规则注解
     * mocker: 当有多个含有{@link Mocker}的规则注解时。则将忽略所有的模拟规则注解
     *          当只有模拟规则注解时。则将使用此模拟规则注解进行数据模拟.
     *
     * 故此属性表现为无对应的模拟规则注解。
     */
    @ChineseName
    @Requires
    public String multiple;

    /**
     * 3. 同时添加多条注解规则：(使用{@link Target}做指定)
     *
     * 此种方式将使用通过{@link Target}做了特殊指定的规则注解。
     */
    @Requires
    @ChineseName
    @Target(
            mock = ChineseName.class,
            checks = {ChineseName.class}
    )
    public String multipleWithTarget;

    @Override
    public String toString() {
        return "Entity{" +
                "single='" + single + '\'' +
                ", multiple='" + multiple + '\'' +
                ", multipleWithTarget='" + multipleWithTarget + '\'' +
                '}';
    }
}
