package com.vang.productservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, String> {

    @Query(value = "select pr.productid from products pr order by pr.productid desc limit 1", nativeQuery = true)
    String getLatestProductId();

    //into store proc is select * statement get all products
    @Query(value = "call findBySellerId(?1)", nativeQuery = true)
    List<Products> getAllBySellerId(String sellerId);
}