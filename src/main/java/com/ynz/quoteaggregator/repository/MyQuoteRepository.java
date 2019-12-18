package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.MyQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MyQuoteRepository extends JpaRepository<MyQuote, UUID> {

    @Query("select q,u from MyQuote q join q.user u where u.loginName = :loginName")
    List<MyQuote> findQuoteByUserLoginName(@Param("loginName") String user);
}
