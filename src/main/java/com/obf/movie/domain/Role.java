package com.obf.movie.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@SequenceGenerator(
    name = "rolesSeq",
    sequenceName = "roles_seq",
    allocationSize = 1,
    schema = "MOVIENEWDBA"
)
@Table(
    name = "roles",
    schema = "MOVIENEWDBA"
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

    @Size(
        max = 100
    )
    @Column(
        name = "caracter_name"
    )
    private String caracterName;


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

    public String getCaracterName() {
        return caracterName;
    }

    public void setCaracterName(String caracterName) {
        this.caracterName = caracterName;
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
