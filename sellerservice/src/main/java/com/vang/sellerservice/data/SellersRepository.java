package com.vang.sellerservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellersRepository extends JpaRepository<Sellers, String> {

    @Query(value = "select s.sellerid from sellers s order by s.sellerid desc limit 1", nativeQuery = true)
    String getLastestId();

    @Query(value = "select count(s.username) from sellers s where s.username = ?1", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(s.email) from sellers s where s.email = ?1", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select count(s.phone) from sellers s where s.phone = ?1", nativeQuery = true)
    long countByPhone(String phone);

    @Query(value = "select count(s.email) from sellers s where s.email =?1 and s.email != ?2", nativeQuery = true)
    long countByEmailToUpdate(String email, String oldEmail);

    @Query(value = "select count(s.phone) from sellers s where s.phone =?1 and s.phone != ?2", nativeQuery = true)
    long countByPhoneToUpdate(String phone, String oldPhone);

    @Query(value = "select count(s.sellerid) from sellers s where s.username = ?1 or s.email = ?1 or s.phone = ?1 and activestatus = 1", nativeQuery = true)
    long countByKeyLogin(String keylogin);

    @Query(value = "select s.password from sellers s where s.username = ?1 or s.email = ?1 or s.phone = ?1 and activestatus = 1", nativeQuery = true)
    String getPasswordByUsername(String username);

    @Query(value = "select s.sellerid,s.firstname,s.lastname,s.username,s.email,s.confirmcode,s.confirmcodeexpiration,s.phone,s.password,s.role,s.createddate,s.lastmodified,s.dateofbirth,s.activestatus,s.avatar,s.shopname,s.shopnameexpiration from sellers s where s.username = ?1 and s.activestatus = 1", nativeQuery = true)
    Sellers findByUsername(String username);

}