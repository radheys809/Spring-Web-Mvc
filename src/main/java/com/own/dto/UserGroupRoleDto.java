package com.own.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserGroupRoleDto {
	private String name;
	private String password;
	private boolean enabled;
	private String type;
	private String roleName;
	
}
