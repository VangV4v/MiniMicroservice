package com.vang.brandservice.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brands")
@Data
@NamedStoredProcedureQuery(procedureName = "autoBrandId", name = "autoBrandId", parameters = {
        @StoredProcedureParameter(name = "brandId", mode = ParameterMode.OUT, type = String.class)
})
public class Brands {

    @Id
    @Column(name = "brandid")
    private String brandid;
    @Column(name = "brandname")
    private String brandname;
    @Column(name = "description")
    private String description;
    @Column(name = "activestatus")
    private Integer activestatus;
}