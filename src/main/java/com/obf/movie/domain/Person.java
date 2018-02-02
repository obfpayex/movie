package com.obf.movie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@SequenceGenerator(
    name = "personsSeq",
    sequenceName = "persons_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "persons",
    schema = "MOVIEDBA" // kan droppes
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Person extends DomainBase {

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
    private Date born;

    @Column(
        name = "deceased"
    )
    private Date deceased;

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
}
