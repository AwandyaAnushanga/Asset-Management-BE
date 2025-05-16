package com.nexeyo.erp.DisposeAsset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposeAssetRepo extends JpaRepository<DisposeAsset, Integer> {
}
