package com.nexeyo.erp.AssetTransfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTransferRepo extends JpaRepository<AssetTransfer, Integer> {
}
