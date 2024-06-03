package com.vang.customerservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface CustomersRepository extends JpaRepository<Customers, String> {

    @Query(value = "call authCustomer(?1)", nativeQuery = true)
    Customers findByLoginType(String typeLogin);

    @Query(value = "select cus.password from customers cus where cus.username = ?1 and activestatus = 1", nativeQuery = true)
    String getPasswordByUsername(String username);

    @Query(value = "select count(username) from customers where username = ?1 and activestatus = 1", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(phone) from customers where phone = ?1 and activestatus = 1", nativeQuery = true)
    long countByPhone(String phone);

    @Query(value = "select count(email) from customers where email = ?1 and activestatus = 1", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select customerid from customers  order by customerid desc limit 1", nativeQuery = true)
    String getLatestCustomerId();

    @Query(value = "select count(cus.email) from customers cus where cus.email = ?1 and email != ?2 and activestatus = 1",nativeQuery = true)
    long countByEmailForUpdate(String newEmail, String oldEmail);

    @Query(value = "select count(cus.phone) from customers cus where cus.phone = ?1 and phone != ?2 and activestatus = 1",nativeQuery = true)
    long countByPhoneForUpdate(String newPhone, String oldPhone);

    @Query(value = "select cus.customerid from customers cus where cus.username = ?1 and activestatus = 1", nativeQuery = true)
    String getCustomerIdByUsername(String username);

}