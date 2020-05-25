package com.own.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "user_group")
@Data
public class Groups implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 273671175143807857L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_Group")
	@SequenceGenerator(sequenceName = "Seq_Group", allocationSize = 1, name = "Seq_Group")
	@Column(updatable = false)
	private Long groupId;
	private String type;
	private String description;
	private String asignedBy;
	@OneToMany
	@JoinColumn(name = "roleId")
	private Collection<RolesMaster> roles=new LinkedHashSet<>();
	
}
