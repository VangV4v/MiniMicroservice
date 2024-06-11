package com.vang.orderservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String> {

    @Query(value = "SELECT od.orderid,od.orderdate,od.productid,od.customerid,od.customerdetail,od.productdetail,od.addressid,od.addressdetail,od.sellerid,od.note,od.quantity,od.price,od.totalprice,od.confirmstatus  FROM orders od where od.customerid = ?1 and od.confirmstatus = 1", nativeQuery = true)
    List<Orders> findAllByCustomerId(String customerId);
}
