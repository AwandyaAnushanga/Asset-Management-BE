package com.nexeyo.erp.Incoterms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncotermsRepo extends JpaRepository<Incoterms,Integer> {

}
