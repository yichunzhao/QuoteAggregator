package com.ynz.quoteaggregator.service;

import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;

import java.util.List;

public interface MyQuoteService {
    MyQuote saveUserMyQuote(MyQuote myQuote);
    List<MyQuote> findUserMyQuoteByLoginName(String loginName);
    User findUserByLoginName(String loginName);

}
