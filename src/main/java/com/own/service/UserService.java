package com.own.service;

import com.own.dto.UserDto;
import com.own.dto.UserRegDto;
import com.own.exeptions.EmptyResource;
import com.own.exeptions.UserExeption;
import org.springframework.cache.annotation.Cacheable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;

public interface UserService {
    @Cacheable(value = "user-cache", key = "#name")
    UserDto getByuserName(String name);

    UserDto getByuserNameUsingSP(String name);

    Object getEmailUsingCurser(String email);

    String deleteUserById(long id) throws UserExeption;

    ByteArrayInputStream generateReportAsPdf(int page, int size) throws EmptyResource;

    String doRegister(@Valid @NotNull UserRegDto userReq);

    @Cacheable("alluserscache")
    String getAllUsers();

}
