package com.tsystems.javaschool.loginov.logiweb.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Parent java bean that will hold the common information.
 */
@MappedSuperclass
public abstract class AbstractModel {

    @CreationTimestamp
    @Column(name = "created_time")
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    public AbstractModel() {
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
