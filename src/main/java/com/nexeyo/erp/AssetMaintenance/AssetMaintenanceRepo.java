package com.nexeyo.erp.AssetMaintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetMaintenanceRepo extends JpaRepository<AssetMaintenance, Integer> {
}
