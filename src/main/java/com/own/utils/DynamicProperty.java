package com.own.utils;

import java.lang.reflect.Proxy;

public class DynamicProperty {

	public static <T extends Identification> T create(Class<T> clazz) {
		T object = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz},
				new Handler(clazz));
        
		return object;
	}
}
