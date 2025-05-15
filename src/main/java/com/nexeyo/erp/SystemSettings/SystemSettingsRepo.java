package com.nexeyo.erp.SystemSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemSettingsRepo extends JpaRepository<SystemSettings,Integer> {
    @Query("select s from SystemSettings s where upper(s.field) = upper(?1)")
    Optional<SystemSettings> findByFieldIgnoreCase(String field);

    @Query("select s from SystemSettings s where s.field = ?1")
    Optional<SystemSettings> findByField(String field);

    @Query("select s from SystemSettings s where s.field_value = 10")
    Optional<SystemSettings> findWareHouseId();
}
