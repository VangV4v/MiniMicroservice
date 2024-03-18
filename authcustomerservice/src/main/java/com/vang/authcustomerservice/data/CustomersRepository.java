package com.vang.authcustomerservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface CustomersRepository extends JpaRepository<Customers, String> {

    @Query(value = "call authCustomer(?1)", nativeQuery = true)
    Customers findByLoginType(String typeLogin);
}