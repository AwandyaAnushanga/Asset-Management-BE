package com.nexeyo.erp.jwt.repository;

import com.nexeyo.erp.jwt.models.ERole;
import com.nexeyo.erp.jwt.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("select count(u) from User u where u.create_at >= ?1 and u.create_at <= ?2")
  long countByCreate_atGreaterThanEqualAndCreate_atLessThanEqual(LocalDateTime create_at, LocalDateTime create_at1);
  @Query("select u from User u inner join u.roles roles where roles.name = ?1")
  List<User> findByRoles_Name(ERole name, Pageable pageable);
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

    @Query("select u from User u inner join u.usersLocationList usersLocationList " +
            "where usersLocationList.location.id = ?1")
    List<User> findByUsersLocationList_Location_id(Integer location_id);
}
