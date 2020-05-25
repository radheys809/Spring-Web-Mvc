package com.own.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Blocked_User_Operations")
@Getter
@Setter
public class BlockedOperations implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4098780450431547322L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_Blocked_Opreations")
	@SequenceGenerator(sequenceName = "Seq_Blocked_Opreations", allocationSize = 1, name = "Seq_Blocked_Opreations")
	private Long blockId;
	private String reason;
	private boolean isTemp;
	@ManyToOne
	@JoinColumn(name="opeId", nullable=false,foreignKey =
			@ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT,name = "FK_Opration_BLK"))
	private OperationsMaster operation;
	private Date blockingDate;
	private boolean isExpirable;
	private Date expiryDate;

}
