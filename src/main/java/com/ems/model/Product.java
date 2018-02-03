package com.ems.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Entity
public class Product extends BasicEntity {

    @Column(name = "name", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Name of the product")
    private String product;

    @Column(name = "description", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Discription of the product")
    private String description;

    @Column(name = "quantity", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Quantity of the product")
    private String quantity;


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
