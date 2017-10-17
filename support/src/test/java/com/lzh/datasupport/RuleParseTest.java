package com.lzh.datasupport;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.support.rules.Requires;
import com.lzh.datasupport.core.annotation.Target;
import com.lzh.datasupport.core.model.Mapping;
import com.lzh.datasupport.rules.ChineseName;
import com.lzh.datasupport.rules.ChineseNameSupport;
import com.lzh.datasupport.tools.Cache;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author haoge on 2017/10/13.
 */
public class RuleParseTest {

    /**
     * 1. 单一规则：直接使用对应注解中所使用的规则
     */
    @ChineseName
    String simple;

    /**
     * 2. 同时添加多条注解规则：(未使用{@link Target}做特殊指定)
     *
     * checker: 所有含有{@link Checker}的规则注解
     * mocker: 由上至下遍历所有规则注解。使用最后一个有{@link Mocker}的规则注解
     */
    @ChineseName
    @Requires
    String multiple;

    /**
     * 3. 同时添加多条注解规则：(使用{@link Target}做指定)
     *
     * 此种方式将使用通过{@link Target}做了特殊指定的规则注解。
     */
    @Requires
    @ChineseName
    @Target(
            mock = Requires.class,
            checks = {ChineseName.class}
    )
    String multipleWithTarget;

    @Test
    public void ruleParse() throws Exception {
        List<Mapping> mappings = Cache.findOrCreateMappingList(RuleParseTest.class);

        Mapping simple = findMapping("simple", mappings);
        assertEquals(simple.checks[0].checks[0], ChineseNameSupport.class);
        assertEquals(simple.mock.mock, ChineseNameSupport.class);

        Mapping multiple = findMapping("multiple", mappings);
        assertEquals(multiple.checks.length, 2);
        assertEquals(multiple.checks[0].annotation.annotationType(), Requires.class);
        assertEquals(multiple.checks[1].annotation.annotationType(), ChineseName.class);
        assertEquals(multiple.mock.annotation.annotationType(), ChineseName.class);

        Mapping multipleWithTarget = findMapping("multipleWithTarget", mappings);
        assertEquals(multipleWithTarget.checks.length, 1);
        assertEquals(multipleWithTarget.checks[0].annotation.annotationType(), ChineseName.class);
        assertEquals(multipleWithTarget.mock.annotation.annotationType(), Requires.class);
    }

    private Mapping findMapping(String fieldName, List<Mapping> mappings) {
        for (Mapping mapping : mappings) {
            if (mapping.field.getName().equals(fieldName)) {
                return mapping;
            }
        }
        throw new RuntimeException("Could not find mapping with this field:" + fieldName);
    }
}
