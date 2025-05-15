package com.nexeyo.erp.HRDepartment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HRDepartmentRepo extends JpaRepository<HRDepartment,Integer> {
    @Query("select h from HRDepartment h where h.department_name like concat('%', ?1, '%') order by h.id DESC")
    List<HRDepartment> findByDepartment_nameContainsOrderByIdDesc(String department_name, Pageable pageable);
    @Query("select h from HRDepartment h where h.department_name = ?1")
    List<HRDepartment> findByDepartment_name(String department_name, Pageable pageable);
    @Query("select h from HRDepartment h where h.department_id = ?1")
    Optional<HRDepartment> findByDepartment_id(Integer department_id);
}
