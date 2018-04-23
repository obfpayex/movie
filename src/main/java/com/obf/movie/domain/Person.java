package com.obf.movie.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@SequenceGenerator(
    name = "personsSeq",
    sequenceName = "persons_seq",
    allocationSize = 1,
    schema = "MOVIENEWDBA"
)
@Table(
    name = "persons",
    schema = "MOVIENEWDBA" // kan droppes
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Person {

    @NotNull
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "personsSeq"
    )
    @Column(
        name = "oid",
        nullable = false
    )
    private Long oid;

    @Size(
        max = 50
    )
    @Column(
        name = "first_name",
        nullable = false
    )
    private String firstName;

    @NotNull
    @Size(
        max = 150
    )
    @Column(
        name = "last_name",
        nullable = false
    )
    private String lastName;


    @Column(
        name = "born"
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date born;

    @Column(
        name = "deceased"
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deceased;

    @Column(
        name = "created",
        nullable = false
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created;

    @Column(
        name = "modified",
        nullable = false
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date modified;

    @NotNull
    @Size(
        max = 40
    )
    @Column(
        name = "created_by",
        nullable = false
    )
    private String createdBy;

    @NotNull
    @Size(
        max = 40
    )
    @Column(
        name = "modified_by",
        nullable = false
    )
    private String modifiedBy;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Country bornCountry;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Date getDeceased() {
        return deceased;
    }

    public void setDeceased(Date deceased) {
        this.deceased = deceased;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Country getBornCountry() {
        return bornCountry;
    }

    public void setBornCountry(Country bornCountry) {
        this.bornCountry = bornCountry;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
