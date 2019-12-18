package com.ynz.quoteaggregator.dto;

import com.ynz.quoteaggregator.entities.User;

public class UserDto {
    private String loginName;

    public UserDto(){}

    public UserDto(User user) {
        setLoginName(user.getLoginName());
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public User toModel() {
        User user = new User();
        user.setLoginName(getLoginName());
        return user;
    }
}
