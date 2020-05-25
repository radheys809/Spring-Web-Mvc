//package com.own.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.own.models.User;
//
//public class MyUserDetails implements UserDetails {
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
//	private User user ;
// 	
//	public MyUserDetails() {
//	}
//
//	public MyUserDetails(User user) {
//		this.setUser(user);
//		this.userName=user.getName();
//		this.passWord=user.getPassword();
//		this.active=user.isEnabled();
//		operations=new ArrayList<>(); 
////		user.getGroup().getRoles().stream()
//////				.filter(role-> !role.getRoleName().equals(null))
////				.findAny().ifPresent(o-> 
////				operations.add(new SimpleGrantedAuthority(o.getRoleName()))
////				);
////		user.getGroup().getRoles().stream()
////				.map(role-> role.getOperations().stream())
////				.flatMap(op -> op.filter(name-> !name.getOperationName().equals(null))).
////				findAny().ifPresent(o-> operations.add(new SimpleGrantedAuthority(o.getOperationName())));
////		
//				}
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return operations;
//	}
//
//	@Override
//	public String getPassword() {
//		return passWord;
//	}
//
//	@Override
//	public String getUsername() {
//		return userName;
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
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//}
