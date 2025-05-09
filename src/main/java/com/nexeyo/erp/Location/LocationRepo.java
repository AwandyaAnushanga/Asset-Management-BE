package com.nexeyo.erp.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

    @Query(nativeQuery = true, value = "SELECT max(branch_code_without_characters) FROM erp.location")
    Integer getMax();

    @Query("select l from Location l where l.enabled = true")
    List<Location> findByEnabledTrue();

    List<Location> findByIdAndEnabled(Integer id, Boolean enabled);
}
