package com.nexeyo.erp.AssetHeader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetHeaderRepo extends JpaRepository<AssetHeader, Integer> {
}
