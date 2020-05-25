//package com.own.service.impl;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.own.dto.UserGroupRoleDto;
//
//import lombok.Getter;
//import lombok.ToString;
//
//@ToString
//@Getter
//public  class MUserDetails implements UserDetails{
//
//	/**
//	 * 
//	 */
//	
//	private static final long serialVersionUID = 109388085332644130L;
//	
//	private String userName;
//	private String passWord;
//	private boolean active;
//	private List<SimpleGrantedAuthority> operations;
// 	private String authToken;
//	public MUserDetails() {
//	}
//
//	public MUserDetails(UserGroupRoleDto user) {
//		this.userName=user.getName();
//		this.passWord=user.getPassword();
//		this.active=user.isEnabled();
//		operations=
//				Arrays.
//				asList(new 
//						SimpleGrantedAuthority
//						(user.getRoleName()));
////		user.getGroup()
////				.getRoles().
////					stream()
////					.filter(role-> 
////							!role.getRoleName().equals(null))
////						.findAny().ifPresent(o-> 
////							operations.add(
////									new SimpleGrantedAuthority
////									(o.getRoleName()))
////				);
//		
//				}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return operations;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.passWord;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return active;
//	}
//}
