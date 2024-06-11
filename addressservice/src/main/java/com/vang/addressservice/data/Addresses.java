package com.vang.addressservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Addresses {

    @Id
    @Column(name = "addressid")
    private String addressid;
    @Column(name = "customerid")
    private String customerid;
    @Column(name = "addressdetail")
    private String addressdetail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private String note;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "lastmodified")
    private String lastmodified;
}