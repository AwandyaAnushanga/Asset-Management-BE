package com.nexeyo.erp.DisposeTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposeTypesRepo extends JpaRepository<DisposeTypes, Integer> {
}
