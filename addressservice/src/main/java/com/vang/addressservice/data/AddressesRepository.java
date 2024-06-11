package com.vang.addressservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressesRepository extends JpaRepository<Addresses, String> {

    @Query(value = "select ad.addressid from addresses ad order by ad.addressid desc limit 1", nativeQuery = true)
    String getLatestId();

    @Query(value = "select ad.addressid,ad.customerid,ad.addressdetail,ad.phone,ad.name,ad.note,ad.createddate,ad.lastmodified from addresses ad where ad.customerid = ?1", nativeQuery = true)
    List<Addresses> findAllByCustomerId(String customerId);
}