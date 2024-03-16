package com.vang.brandservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface BrandsRepository extends JpaRepository<Brands, String> {

    @Procedure("autoBrandId")
    String generateId();
}