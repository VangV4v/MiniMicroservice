package com.vang.confirmorderservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String> {

    @Query(value = "SELECT od.orderid,od.orderdate,od.productid,od.customerid,od.customerdetail,od.productdetail,od.addressid,od.addressdetail,od.sellerid,od.note,od.quantity,od.price,od.totalprice,od.confirmstatus  FROM orders od where od.sellerid = ?1 && od.confirmstatus = 0", nativeQuery = true)
    List<Orders> findAllBySellerIdAndStatus(String sellerId);

    @Query(value = "SELECT od.orderid,od.orderdate,od.productid,od.customerid,od.customerdetail,od.productdetail,od.addressid,od.addressdetail,od.sellerid,od.note,od.quantity,od.price,od.totalprice,od.confirmstatus  FROM orders od where od.sellerid = ?2 && od.orderid = ?1", nativeQuery = true)
    Orders findByIdAndSellerId(String orderId, String sellerId);

    @Query(value = "SELECT od.orderid,od.orderdate,od.productid,od.customerid,od.customerdetail,od.productdetail,od.addressid,od.addressdetail,od.sellerid,od.note,od.quantity,od.price,od.totalprice,od.confirmstatus  FROM orders od where od.sellerid = ?1", nativeQuery = true)
    List<Orders> findAllBySellerId(String sellerId);
}
