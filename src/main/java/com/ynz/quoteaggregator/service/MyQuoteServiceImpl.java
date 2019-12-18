package com.ynz.quoteaggregator.service;

import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;
import com.ynz.quoteaggregator.repository.MyQuoteRepository;
import com.ynz.quoteaggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class MyQuoteServiceImpl implements MyQuoteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyQuoteRepository myQuoteRepository;

    @Override
    public MyQuote saveUserMyQuote(MyQuote myQuote) {
        MyQuote saved = myQuoteRepository.save(myQuote);
        return saved;
    }

    @Override
    public List<MyQuote> findUserMyQuoteByLoginName(String loginName) {

        List<MyQuote> result = myQuoteRepository
                .findAll()
                .stream()
                .filter(x -> x.getUser().getLoginName().equals(loginName))
                .sorted((Comparator.comparingInt(MyQuote::getRating)).reversed())
                .collect(toList());

        return result;
    }

    @Override
    public User findUserByLoginName(String loginName) {
        return userRepository.findUserByLoginName(loginName);
    }
}
