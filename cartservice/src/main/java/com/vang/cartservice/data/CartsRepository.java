package com.vang.cartservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartsRepository extends JpaRepository<Carts, String> {

    @Query(value = "select c.cartid from carts c order by c.cartid desc limit 1", nativeQuery = true)
    String getLatestId();

    @Query(value = "delete from carts where customerid = ?1", nativeQuery = true)
    @Modifying
    void deleteAllByCustomerId(String customerId);

    @Query(value = "select c.cartid,c.customerid,c.productid,c.productdetail,c.quantity from carts c where c.customerid = ?1 and c.productid = ?2", nativeQuery = true)
    Carts findByCustomerAndProduct(String customerId, String productId);

    @Query(value = "select c.cartid,c.customerid,c.productid,c.productdetail,c.quantity from carts c where c.customerid = ?1", nativeQuery = true)
    List<Carts> findAllByCustomerId(String customerId);
}