package com.ynz.quoteaggregator.service;

import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;
import com.ynz.quoteaggregator.entities.UserRole;

import java.util.List;
import java.util.Set;

public interface MyQuoteService {
    MyQuote saveUserMyQuote(MyQuote myQuote);

    List<MyQuote> findUserMyQuoteByLoginName(String loginName);

    User findUserByLoginName(String loginName);

    Set<UserRole> findUserRolesByLoginName(String loginName);

    UserRole saveUserRole(UserRole userRole);

}
