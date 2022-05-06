package com.own.factory;

import com.own.dto.UserRegDto;

public class UserRegDtoBuilder {

    private String name;
    private String email;
    private String password;
    private String msidn;
    private String country;

    public UserRegDtoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserRegDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserRegDtoBuilder msidn(String msidn) {
        this.msidn = msidn;
        return this;
    }

    public UserRegDtoBuilder country(String country) {
        this.country = country;
        return this;
    }

    public UserRegDtoBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserRegDto build() {
        UserRegDto dto = new UserRegDto();
        dto.setCountry(country);
        dto.setEmail(email);
        dto.setMsidn(msidn);
        dto.setName(name);
        dto.setPassword(password);
        return dto;
    }


}
