package com.vang.productservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<Products, String> {

    @Query(value = "select pr.productid from products pr order by pr.productid desc limit 1", nativeQuery = true)
    String getLatestProductId();
}