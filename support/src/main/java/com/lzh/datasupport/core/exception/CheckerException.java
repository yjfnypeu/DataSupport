package com.lzh.datasupport.core.exception;

import com.lzh.datasupport.core.check.ICheck;

import java.lang.reflect.Field;

/**
 * 当进行检查时，若检查失败。将会将失败数据装入此异常中对外提供。便于外部针对性的进行处理
 */
public class CheckerException extends RuntimeException{

    // 用于进行检查时的数据值
    private Object value;
    // 用于进行检查的检查器
    private ICheck check;
    // 对应的被检查字段
    private Field field;

    public CheckerException() {
        super("Check failed:");
    }

    public CheckerException(Throwable throwable) {
        super("Check failed:", throwable);
    }

    public CheckerException set(Object value, ICheck check, Field field) {
        this.value = value;
        this.field = field;
        this.check = check;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public ICheck getCheck() {
        return check;
    }

    public Field getField() {
        return field;
    }
}
