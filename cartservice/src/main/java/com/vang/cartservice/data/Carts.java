package com.vang.cartservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "carts")
@Data
public class Carts {

    @Id
    @Column(name = "cartid")
    private String cartid;
    @Column(name = "customerid")
    private String customerid;
    @Column(name = "productid")
    private String productid;
    @Column(name = "productdetail", length = 2000)
    private String productdetail;
    @Column(name = "quantity")
    private int quantity;
}