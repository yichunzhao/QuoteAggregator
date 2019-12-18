package com.ynz.quoteaggregator.service;

import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;
import com.ynz.quoteaggregator.entities.UserRole;
import com.ynz.quoteaggregator.repository.MyQuoteRepository;
import com.ynz.quoteaggregator.repository.UserRepository;
import com.ynz.quoteaggregator.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class MyQuoteServiceImpl implements MyQuoteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyQuoteRepository myQuoteRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public MyQuote saveUserMyQuote(MyQuote myQuote) {
        MyQuote saved = myQuoteRepository.save(myQuote);
        return saved;
    }

    @Override
    public List<MyQuote> findUserMyQuoteByLoginName(String loginName) {

        List<MyQuote> result = myQuoteRepository
                .findQuoteByUserLoginName(loginName)
                .stream()
                .sorted(Comparator.comparingInt(MyQuote::getRating).reversed())
                .collect(toList());

        return result;
    }

    @Override
    public User findUserByLoginName(String loginName) {
        return userRepository.findUserByLoginName(loginName);
    }

    @Override
    public Set<UserRole> findUserRolesByLoginName(String loginName) {
        return userRoleRepository.findUserRolesByLoginName(loginName);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }
}
