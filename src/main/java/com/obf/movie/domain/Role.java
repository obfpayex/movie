package com.obf.movie.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@SequenceGenerator(
    name = "rolesSeq",
    sequenceName = "roles_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "roles",
    schema = "MOVIEDBA"
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)

public class Role implements Serializable {

    @NotNull
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rolesSeq"
    )
    @Column(
        name = "oid",
        nullable = false
    )
    private Long oid;



    @OneToOne
    private Person person;

    @ManyToOne
    private RoleType roleType;


    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
