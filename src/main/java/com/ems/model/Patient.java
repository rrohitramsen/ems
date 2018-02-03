package com.ems.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Entity
public class Patient extends BasicEntity{


    @Column(name = "name", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Name of the patient")
    private String name;

    @Column(name = "address", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Address of the patient")
    private String address;

    @Column(name = "mobile", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Mobile number of the patient")
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
