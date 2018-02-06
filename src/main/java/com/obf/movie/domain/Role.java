package com.obf.movie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@SequenceGenerator(
    name = "rolesSeq",
    sequenceName = "roles_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "roles",
    schema = "MOVIEDBA" // kan droppes
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Role extends DomainBase {

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

    @NotNull
    @Size(
        max = 150
    )
    @Column(
        name = "title",
        nullable = false
    )
    private String title;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role", cascade = CascadeType.ALL)
//    private Set<MoviePersonRole> moviePersonRole;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Set<MoviePersonRole> getMoviePersonRole() {
//        return moviePersonRole;
//    }
//
//    public void setMoviePersonRole(Set<MoviePersonRole> moviePersonRole) {
//        this.moviePersonRole = moviePersonRole;
//    }
}
