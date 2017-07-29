package com.lzh.datasupport;

import com.lzh.datasupport.pojo.Cyclic;
import com.lzh.datasupport.pojo.Simple;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataSupportTest {

    @Test
    public void mockSimple() throws Exception {
        for (int i = 0; i < 100; i++) {
            Simple simple = DataSupport.create().mock(Simple.class);
            assertTrue(DataSupport.create().check(simple));
        }
    }

    @Test
    public void mockCyclic() throws Exception {
        try {
            DataSupport.create().mock(Cyclic.class);
        } catch (RuntimeException e) {
            assertTrue(e.getCause().getMessage().startsWith("Find an unsupported cyclic dependency links"));
        }
    }

}