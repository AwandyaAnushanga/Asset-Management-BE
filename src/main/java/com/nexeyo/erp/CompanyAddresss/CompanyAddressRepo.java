package com.nexeyo.erp.CompanyAddresss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAddressRepo extends JpaRepository<CompanyAddress,Integer> {
    @Query("select c from CompanyAddress c where c.name = ?1")
    CompanyAddress findByName(String name);
}
