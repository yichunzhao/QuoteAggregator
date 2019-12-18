package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.MyQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MyQuoteRepository extends JpaRepository<MyQuote, UUID> {

//    @Query("select q from MyQuote q, User u where q.loginName = :loginName")
//    List<MyQuote> findQuoteByUserLoginName(@Param("loginName") String user);

}
