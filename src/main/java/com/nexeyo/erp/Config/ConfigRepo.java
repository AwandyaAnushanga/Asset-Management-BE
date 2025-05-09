package com.nexeyo.erp.Config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepo extends JpaRepository<Config,Integer> {
    @Query("select c from Config c where c.key = ?1")
    Config findByKey(String key);

    @Query("select c from Config c where c.key = ?1")
    List<Config> finbyKeyList(String key);
}
