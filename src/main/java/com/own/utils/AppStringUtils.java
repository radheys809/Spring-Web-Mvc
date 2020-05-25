package com.own.utils;

import com.own.exeptions.EmptyResource;
import org.springframework.util.StringUtils;

import java.util.Collection;

public abstract class AppStringUtils extends StringUtils{
	private static final Object EMPTY_STRING="";

	public static <T> T isNullEmpty(T obj){
		if (obj == null)
        throw new NullPointerException();
		else if(EMPTY_STRING==obj)
			throw new NullPointerException("The Element is blank");
		else
    return obj;
	}
	
	public static <E> Collection<E> isEmptyCollection(Collection<E> collection) {
		if (collection.isEmpty())
			throw new EmptyResource("The resource is empty",1001);
		else
			return collection;
	}
	}
