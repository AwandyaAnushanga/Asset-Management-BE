package com.nexeyo.erp.MaintenanceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTypeRepo extends JpaRepository<MaintenanceType, Integer> {
}
