package com.own.service.impl;

import com.own.service.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;


@Getter
@EqualsAndHashCode
public final class UserRole implements Role {

    private String role;

    @Override
    public String getAuthurity() {
        return role;
    }

    public UserRole(@NotNull String role) {
        Assert.hasText(role, "A granted role textual representation is required");
        this.role = role;
    }
}
