package com.nexeyo.erp.PaymentTerms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermsRepo extends JpaRepository<PaymentTerms,Integer> {
}
