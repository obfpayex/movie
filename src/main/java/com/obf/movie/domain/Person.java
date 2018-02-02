package com.obf.movie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.io.Serializable;

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
    private Data born;

    @Column(
        name = "deceased"
    )
    private Data deceased;

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

    public Data getBorn() {
        return born;
    }

    public void setBorn(Data born) {
        this.born = born;
    }

    public Data getDeceased() {
        return deceased;
    }

    public void setDeceased(Data deceased) {
        this.deceased = deceased;
    }
}
