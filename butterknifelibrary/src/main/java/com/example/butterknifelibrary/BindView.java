package com.example.butterknifelibrary;

/**
 * Created by ZhangHaiLong on 2017/9/8.
 * QQ:2281234263
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
Target 给谁添加注解 FIELD

ElementType.FIELD 字段
ElementType.METHOD 方法

@Retention 为生命周期

RetentionPolicy.SOURCE 只保留在源码上面，编译成Class时自动被编译器抹掉

RetentionPolicy.class 保留到字节码上面，VM加载字节码时自动抹除 编译时注解

RetentionPolicy.RUNTIME VM会加载到内存中 运行时注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    /**
     * View ID to which the field will be bound.
     */
    public abstract int value();
}

