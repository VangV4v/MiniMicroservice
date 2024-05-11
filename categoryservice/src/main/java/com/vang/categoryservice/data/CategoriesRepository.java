package com.vang.categoryservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriesRepository extends JpaRepository<Categories, String> {

    @Query(value = "select ca.categoryid from categories ca order by ca.categoryid desc limit 1", nativeQuery = true)
    String getLatestId();
}