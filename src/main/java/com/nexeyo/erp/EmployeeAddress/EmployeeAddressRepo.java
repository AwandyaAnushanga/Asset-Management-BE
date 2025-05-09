package com.nexeyo.erp.EmployeeAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress,Integer> {
}
