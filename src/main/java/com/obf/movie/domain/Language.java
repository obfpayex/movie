package com.obf.movie.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(
    name = "languagesSeq",
    sequenceName = "languages_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "languages",
    schema = "MOVIEDBA"
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Language implements Serializable{
    @NotNull
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "languagesSeq"
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
        name = "text",
        nullable = false
    )
    private String text;

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

    @Size(
        max = 40
    )
    @Column(
        name = "created_by"
    )
    private String createdBy;

    @Size(
        max = 40
    )
    @Column(
        name = "modified_by"
    )
    private String modifiedBy;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}






