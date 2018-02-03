package com.ems.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by rohitkumar on 28/01/18.
 */
@Entity
public class BasicEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "The database generated ID.")
    private Long id;

    @Column(name = "created_time", insertable=true, updatable=false)
    @ApiModelProperty(notes = "The database generated created time.")
    private Date createdTime;

    @Column(name = "updated_time", insertable=false, updatable=true)
    @ApiModelProperty(notes = "The database generated updated time.")
    private Date updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
        updatedTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public String toString() {
        return "BasicEntity{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }

}
