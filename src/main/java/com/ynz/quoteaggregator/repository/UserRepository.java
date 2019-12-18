package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.loginName = :loginName")
    User findUserByLoginName(@Param("loginName") String loginName);
}
