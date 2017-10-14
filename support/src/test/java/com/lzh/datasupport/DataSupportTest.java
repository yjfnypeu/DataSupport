package com.lzh.datasupport;

import com.lzh.datasupport.core.annotation.NonNull;
import com.lzh.datasupport.core.annotation.Requires;
import com.lzh.datasupport.rules.ChineseName;


import org.junit.Test;

import static org.junit.Assert.*;

public class DataSupportTest {

    static DataSupport support = DataSupport.create();

    @Test
    public void mockSimple() throws Exception {
        check(support.mock(Simple.class));
        check(support.mock(Multiple.class));
    }

    private static void check(Object entity) {
        assertTrue(entity != null);
        assertTrue(support.check(entity));
    }

    static class Simple {

        @ChineseName
        String name;
    }

    static class Multiple {

        @NonNull
        @ChineseName
        String name;
    }
}