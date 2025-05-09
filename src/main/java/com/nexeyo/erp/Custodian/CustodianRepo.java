package com.nexeyo.erp.Custodian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustodianRepo extends JpaRepository<Custodian, Integer> {
}
