package com.own.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDto {
	private Long userId;
	private String name;
	private String email;
	private String mobile;
	@JsonPropertyDescription(value = "includes the Json description")
	@JsonProperty("country-Name")
	private String country;
	private boolean enabled;
	public UserDto(Long userId, String name, String email, String mobile, String country) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.country = country;
	}
	
}
