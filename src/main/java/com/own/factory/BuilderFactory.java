package com.own.factory;

public abstract class BuilderFactory {

    public static UserRegDtoBuilder init() {
        return new UserRegDtoBuilder();
    }
}
