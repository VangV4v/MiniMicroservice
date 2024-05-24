package com.vang.brandservice.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brands")
@Data
public class Brands {

    @Id
    @Column(name = "brandid")
    private String brandid;
    @Column(name = "brandname")
    private String brandname;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;
    @Column(name = "activestatus")
    private int activestatus;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "lastmodified")
    private String lastmodified;
}