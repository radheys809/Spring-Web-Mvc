package com.own.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "Operations")
@Getter
@Setter
@ToString
public class OperationsMaster implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4078844095393307315L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_Opreations")
    @SequenceGenerator(sequenceName = "Seq_Opreations", allocationSize = 1, name = "Seq_Opreations")
    private Long opeId;
    private String operationName;
    private String description;
    private Date creationTime;
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false,
            foreignKey =
            @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "FK_ROLE"))
    private RolesMaster role;
    @OneToMany(mappedBy = "blockId")
    private Set<BlockedOperations> blockedOperation;
}
