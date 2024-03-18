package com.vang.customerservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface CustomersRepository extends JpaRepository<Customers, String> {

    @Procedure("autoCustomerId")
    String autoGenerateId();
}