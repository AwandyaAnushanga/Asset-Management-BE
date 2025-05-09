package com.nexeyo.erp.NumberingSystem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NumberingSystemRepo extends JpaRepository<NumberingSystem, Integer> {

    @Query("select n from NumberingSystem n where n.type = :type ")
    Optional<NumberingSystem> findByType (@Param("type") Integer type);

    @Query("select n from NumberingSystem n where n.typeName = :type ")
    Optional<NumberingSystem> findByTypeName(@Param("type") String type);


}
