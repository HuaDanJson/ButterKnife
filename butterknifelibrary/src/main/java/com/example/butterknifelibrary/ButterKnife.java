package com.example.butterknifelibrary;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZhangHaiLong on 2017/9/8.
 * QQ:2281234263
 */

public class ButterKnife {

    public static void init(final Activity activity) {
        Class clazz = activity.getClass();

        //拿到activity的所有字段 放到数组中
        Field[] declaredFields = clazz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            //设置为可访问，暴力反射，即使是私有的也可以访问到
            field.setAccessible(true);
            //获取字段上的注解对象
            BindView myBindView = field.getAnnotation(BindView.class);
            //判断Filed上是否有我们想要的注解
            if (myBindView == null) {
                continue;
            }
            int id = myBindView.value();
            View view = activity.findViewById(id);
            try {
                //将该控件设置给Field对象
                field.set(activity, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //获取该类中所有方法
        Method[] declareMethods = clazz.getDeclaredMethods();
        for (int j = 0; j < declareMethods.length; j++) {
            final Method method = declareMethods[j];
            OnClick myOnClick = method.getAnnotation(OnClick.class);
            if (myOnClick == null) {
                continue;
            }

            int[] value = myOnClick.value();
            for (int k = 0; k < value.length; k++) {
                int id = value[k];
                final View button = activity.findViewById(id);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            //通过反射 回调Click 中具体的方法
                            method.invoke(activity, button);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
