package com.own.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Role_Master")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RolesMaster implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8359247970420848361L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_RoleMaster")
    @SequenceGenerator(sequenceName = "Seq_RoleMaster", allocationSize = 1,
            name = "Seq_RoleMaster")
    @Column(updatable = false)
    private Long roleId;
    private String roleName;
    private String category;
    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_Group"))
    private Groups group;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<OperationsMaster> operations;

}
