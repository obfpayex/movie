package com.obf.movie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.person", cascade=CascadeType.ALL)
//    private Set<MoviePersonRole> moviePersonRole;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_oid", referencedColumnName = "oid"), inverseJoinColumns = @JoinColumn(name = "role_oid", referencedColumnName = "oid"))
//    private Set<Role> roles;


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

//    public Set<MoviePersonRole> getMoviePersonRole() {
//        return moviePersonRole;
//    }
//
//    public void setMoviePersonRole(Set<MoviePersonRole> moviePersonRole) {
//        this.moviePersonRole = moviePersonRole;
//    }

    //    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}
