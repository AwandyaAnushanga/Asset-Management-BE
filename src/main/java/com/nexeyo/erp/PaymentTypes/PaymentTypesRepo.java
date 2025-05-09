package com.nexeyo.erp.PaymentTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypesRepo extends JpaRepository <PaymentTypes,Integer>{
}
