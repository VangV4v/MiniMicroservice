package com.vang.brandservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandsRepository extends JpaRepository<Brands, String> {

    @Query(value = "select br.brandid from brands br where br.activestatus = 1 order by br.brandid desc limit 1", nativeQuery = true)
    String getByBrandIdLatest();
}