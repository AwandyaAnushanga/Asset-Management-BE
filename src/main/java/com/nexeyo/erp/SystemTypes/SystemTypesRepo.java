package com.nexeyo.erp.SystemTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemTypesRepo extends JpaRepository<SystemTypes, Integer> {
}
