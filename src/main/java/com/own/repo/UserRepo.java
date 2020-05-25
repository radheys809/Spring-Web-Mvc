package com.own.repo;

import com.own.dto.UserDto;
import com.own.dto.UserGroupRoleDto;
import com.own.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByNameOrEmail(String name,String email);
	@Query("SELECT new com.own.dto.UserDto(u.id,u.name,u.email,u.mobile,u.country)"
					+ " FROM User u WHERE u.name =:name OR u.email =:email ")
	Optional<UserDto> findUserByName(@Param(value = "name")String name,@Param(value = "email")String email);
	@Query("SELECT new com.own.dto.UserGroupRoleDto(u.name,u.password,u.enabled,g.type,r.roleName)"
					+ " FROM User u join Groups g on g.groupId= u.groupId "
					+ " left outer join RolesMaster r on "
					+ "r.group=g.groupId WHERE u.name =:name "
					+ "OR u.email =:email")
	Optional<UserGroupRoleDto> findUserRole(@Param(value = "name")String name,@Param(value = "email")String email);
	
}
