package com.nexeyo.erp.UsersLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersLocationRepository extends JpaRepository<UsersLocation,Integer> {
    @Query("select count(u) from UsersLocation u where u.user_id = ?1")
    long countByUser_id(Integer user_id);
}
