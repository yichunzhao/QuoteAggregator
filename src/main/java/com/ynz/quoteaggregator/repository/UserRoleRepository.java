package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    @Query("select ur from UserRole ur join ur.user u where u.loginName = :loginName")
    Set<UserRole> findUserRolesByLoginName(@Param("loginName") String loginName);
}
