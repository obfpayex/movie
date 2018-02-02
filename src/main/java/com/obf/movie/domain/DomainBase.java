package com.obf.movie.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class DomainBase implements Serializable {

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
