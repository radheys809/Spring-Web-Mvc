//package com.own.service.impl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.own.dto.UserGroupRoleDto;
//import com.own.repo.UserRepo;
//
//@Service
//public class MyUserDetailService implements UserDetailsService{
//	@Autowired
//	UserRepo userRepo;
//	@Override
//	public MUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserGroupRoleDto> user=userRepo.findUserRole(username,username);
//		user.orElseThrow( ()-> new UsernameNotFoundException(username+" :not found "));
//		return user.map(MUserDetails ::new).get();
//	}
//}
