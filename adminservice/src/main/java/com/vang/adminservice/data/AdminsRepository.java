package com.vang.adminservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface AdminsRepository extends JpaRepository<Admins, String> {

    @Procedure("autoIdAdmin")
    String autoGenerateIdAdmin();

    @Query(value = "call authAdmin(?1)",nativeQuery = true)
    Admins findByLoginKey(String key);
}