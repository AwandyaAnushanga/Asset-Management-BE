package com.nexeyo.erp.HRUserFetch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HRUserFetchRepo extends JpaRepository<HRUserFetch,Integer> {
    @Query("select h from HRUserFetch h " +
            "where( h.picked_for_erp = false or h.picked_for_erp is null) and (h.first_name like concat('%', ?1, '%') or h.last_name like concat('%', ?2, '%'))")
    Page<HRUserFetch> findByPicked_for_erpFalseAndPicked_for_erpNullAndFirst_nameContainsAndLast_nameContains(String first_name, String last_name, Pageable pageable);
    @Query("select h from HRUserFetch h " +
            "where (h.first_name like concat('%', ?1, '%') or h.last_name like concat('%', ?2, '%')) and h.user_role_id = ?3")
    List<HRUserFetch> findByFirst_nameContainsOrLast_nameContainsAndUser_role_id(String first_name, String last_name, Integer user_role_id, Pageable pageable);
    @Query("select h from HRUserFetch h where h.department_id = ?1")
    Optional<HRUserFetch> findByDepartment_id(Integer department_id);
    @Query("select h from HRUserFetch h " +
            "where h.first_name like concat('%', ?1, '%') and h.last_name like concat('%', ?2, '%') " +
            "order by h.id DESC")
    List<HRUserFetch> findByFirst_nameContainsAndLast_nameContainsOrderByIdDesc(String first_name, String last_name, Pageable pageable);
    @Query("select h from HRUserFetch h where h.user_id = ?1")
    Optional<HRUserFetch> findByUser_id(Integer user_id);

//    @Query(
//            value = "SELECT  h.* FROM HRUserFetch h " +
//                    "WHERE h.user_id LIKE %:emp_id% " +
//                    "ORDER BY h.id DESC",
//            nativeQuery = true)
//    Page<HRUserFetch> findByUserContainsIgnoreCase(@Param("emp_id") String emp_id, Pageable pageable);

    @Query("select i from HRUserFetch i where upper(i.user_id) like upper(concat('%', ?1, '%'))")
    List<HRUserFetch> findByUserContainsIgnoreCase(String emp_id, Pageable pageable);


}
