package com.own.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class UserExeption extends Exception {


    /**
     *
     */
    private static final long serialVersionUID = 7292262357198927253L;

    private final Integer code;
    private final String message;

}
