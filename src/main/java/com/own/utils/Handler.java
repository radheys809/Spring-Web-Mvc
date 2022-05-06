package com.own.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {

    Object obj;

    public Handler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = null;
        String defaultValue = null;

        try {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Key) {
                    key = ((Key) annotation).value();
                } else if (annotation instanceof DefaultKey) {
                    defaultValue = ((DefaultKey) annotation).value();
                }
            }
            PropertyLoader p = new PropertyLoader();
            String ret = p.get(key);
            return (null == ret) ? defaultValue : ret;
        } catch (Exception e) {

        }
        return null;
    }
}
