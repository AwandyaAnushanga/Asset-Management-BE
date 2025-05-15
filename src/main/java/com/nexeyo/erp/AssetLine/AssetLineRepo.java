package com.nexeyo.erp.AssetLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLineRepo extends JpaRepository<AssetLine, Integer> {
}
