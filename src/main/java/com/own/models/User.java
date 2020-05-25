package com.own.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
//@NamedStoredProcedureQueries({
//		@NamedStoredProcedureQuery(name = 
//				"findByNameProc", procedureName = "getUserByName", resultClasses =(User.class), parameters = {
//						@StoredProcedureParameter(name = "uname", type = String.class, mode = ParameterMode.IN) }),
//		@NamedStoredProcedureQuery(name = "emailCurser", procedureName = "usercurs", resultClasses = (User.class), parameters = {
//				@StoredProcedureParameter(name = "emailList", type = String.class, mode = ParameterMode.INOUT) }),
//		@NamedStoredProcedureQuery(name = "deleteByIdProc", procedureName = "deleteById",  parameters = {
//				@StoredProcedureParameter(name = "uid", type = Long.class, mode = ParameterMode.IN) })
//
//})
@Builder
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7575996264107317483L;
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(updatable = false)
	private Long userId;
	private String name;
	@Size(max = 35)
	private String email;
	@Size(max = 13)
	private String mobile;
	private String country;
	private String password;
	@Size(max = 8,min = 6)
	private String passHas;
	private boolean enabled;
	private Long groupId;
	
	@ManyToMany
	@JoinTable(name = "User_Blocked",
	joinColumns = {@JoinColumn(name="blockId",
	referencedColumnName = "userId")}, 
		inverseJoinColumns = {
				 @JoinColumn(name="userId",
						 referencedColumnName = "blockId") } )
	private Set<BlockedOperations>blockedOperations=new HashSet<BlockedOperations>();;
	
}
