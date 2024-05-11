package com.vang.adminservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface AdminsRepository extends JpaRepository<Admins, String> {

    @Procedure("autoIdAdmin")
    String autoGenerateIdAdmin();

    @Query(value = "call authAdmin(?1)",nativeQuery = true)
    Admins findByLoginKey(String key);

    @Query(value = "select ad.adminid from admins ad order by ad.adminid desc limit 1", nativeQuery = true)
    String getLatestId();

    @Query(value = "select count(ad.email) from admins ad where ad.email = ?1", nativeQuery = true)
    long getCountByEmail(String email);

    @Query(value = "select count(ad.phone) from admins ad where ad.phone = ?1", nativeQuery = true)
    long getCountByPhone(String phone);

    @Query(value = "select count(ad.email) from admins ad where ad.email = ?1 and ad.email != ?2", nativeQuery = true)
    long getCountByEmailToUpdate(String email, String oldEmail);

    @Query(value = "select count(ad.phone) from admins ad where ad.phone = ?1 and ad.phone != ?2", nativeQuery = true)
    long getCountByPhoneToUpdate(String phone, String oldPhone);
}