package com.vang.categoryservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
public class Categories {

    @Id
    @Column(name = "categoryid")
    private String categoryid;
    @Column(name = "categoryname")
    private String categoryname;
    @Column(name = "description")
    private String description;
    @Column(name = "activestatus")
    private int activestatus;
    @Column(name = "createddate")
    private LocalDateTime createddate;
    @Column(name = "lastmodified")
    private LocalDateTime lastmodified;
}
