package com.obf.movie.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movie_person_role", catalog = "moviedba")
@AssociationOverrides({
    @AssociationOverride(name = "pk.movie",
        joinColumns = @JoinColumn(name = "movie_oid")),
    @AssociationOverride(name = "pk.person",
        joinColumns = @JoinColumn(name = "person_oid")),
    @AssociationOverride(name = "pk.role",
        joinColumns = @JoinColumn(name = "role_oid"))})
public class MoviePersonRole implements Serializable{

    @EmbeddedId
    private MoviePersonRoleId pk = new MoviePersonRoleId();

    public MoviePersonRoleId getPk() {
        return pk;
    }

    public void setPk(MoviePersonRoleId pk) {
        this.pk = pk;
    }

    @Transient
    public Movie getMovie(){
        return getPk().getMovie();
    }

    public void setMovie(Movie movie){
            getPk().setMovie(movie);
    }

    @Transient
    public Person getPerson(){
        return getPk().getPerson();
    }

    public void setPerson(Person person){
        getPk().setPerson(person);
    }

    @Transient
    public Role getRole(){
        return getPk().getRole();
    }

    public void setRole(Role role){
        getPk().setRole(role);
    }

}
