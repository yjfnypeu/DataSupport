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

    public DataSupport throwable(boolean throwable) {
        this.throwable = throwable;
        return this;
    }

    public boolean check(Object entity) {
        try {
            return DataChecker.check(entity);
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException(e);
            }
            return false;
        }
    }

    public <T> T mock(Class<T> clz) {
        try {
            return DataMocker.mock(clz);
        } catch (Exception e) {
            if (throwable) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
