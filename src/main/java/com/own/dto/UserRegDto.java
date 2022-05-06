package com.own.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRegDto {
    private String name;
    private String email;
    private String password;
    private String msidn;
    private String country;
}
