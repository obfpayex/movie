package com.obf.movie.domain;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MoviePersonRoleId implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Role role;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        MoviePersonRoleId that = (MoviePersonRoleId) o;
//
//        if (movie != null ? !movie.equals(that.movie) : that.movie != null) return false;
//        if (person != null ? !person.equals(that.person) : that.person != null) return false;
//        if (role != null ? !role.equals(that.role) : that.role != null) return false;
//
//        return true;
//    }

//    public int hashCode() {
//        int result;
//        result = (stock != null ? stock.hashCode() : 0);
//        result = 31 * result + (category != null ? category.hashCode() : 0);
//        return result;
//    }
}
