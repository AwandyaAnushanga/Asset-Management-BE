package com.nexeyo.erp.DeliveryTerms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTermsRepo extends JpaRepository<DeliveryTerms,Integer> {
}
