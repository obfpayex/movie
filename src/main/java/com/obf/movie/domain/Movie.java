package com.obf.movie.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@SequenceGenerator(
    name = "moviesSeq",
    sequenceName = "movies_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "movies",
    schema = "MOVIEDBA"
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)

public class Movie implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "moviesSeq"
    )
    @Column(
        name = "oid",
        nullable = false
    )
    private Long oid;
    @NotNull
    @Size(
        max = 250
    )
    @Column(
        name = "original_title",
        nullable = false
    )
    private String originalTitle;
    @NotNull
    @Size(
        max = 250
    )
    @Column(
        name = "norwegian_title",
        nullable = false
    )
    private String norwegianTitle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_oid", referencedColumnName = "oid"), inverseJoinColumns = @JoinColumn(name = "category_oid", referencedColumnName = "oid"))
    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "category_oid")
    private Set<Category> categories;

    @Column(
        name = "created",
        nullable = false
    )
    private Date created;

    @Column(
        name = "modified",
        nullable = false
    )
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




    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getNorwegianTitle() {
        return norwegianTitle;
    }

    public void setNorwegianTitle(String norwegianTitle) {
        this.norwegianTitle = norwegianTitle;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
