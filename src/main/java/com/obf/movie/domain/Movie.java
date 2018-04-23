package com.obf.movie.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@SequenceGenerator(
    name = "moviesSeq",
    sequenceName = "movies_seq",
    allocationSize = 1,
    schema = "MOVIENEWDBA"
)
@Table(
    name = "movies",
    schema = "MOVIENEWDBA"
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

    @Column(
       name = "running_time"
    )
    private int runningTime;

    @Column(
        name = "release_date"
    )
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    @Size(
        max = 2000
    )
    @Column(
        name = "summary"
    )
    private String summary;

    @Size(
        max = 30
    )
    @Column(
        name = "age_limit"
    )
    private String ageLimit;

    @Column(
        name = "PRODUCTION_YEAR"
    )
    private int productionYear;

    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_oid", referencedColumnName = "oid"), inverseJoinColumns = @JoinColumn(name = "category_oid", referencedColumnName = "oid"))
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Country> originCountry;

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

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Country> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<Country> originCountry) {
        this.originCountry = originCountry;
    }
}
