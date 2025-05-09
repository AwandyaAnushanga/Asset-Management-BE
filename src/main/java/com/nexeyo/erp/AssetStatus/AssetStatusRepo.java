package com.nexeyo.erp.AssetStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetStatusRepo extends JpaRepository<AssetStatus, Integer> {
    @Query("select a from AssetStatus a where a.name = ?1")
    Optional<AssetStatus> findByName(String name);
}
