package com.nexeyo.erp.Technician;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician, Integer> {
}
