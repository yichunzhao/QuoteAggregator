package com.ynz.quoteaggregator.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "must have a login name.")
    @Column(unique = true)
    private String loginName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MyQuote> myQuotes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = UserRole.class)
    private Set<UserRole> userRoles = new HashSet<>();

    public User() {
//        UserRole userRole = new UserRole();
//        userRole.setRole(Role.USER);
//        userRoles.add(userRole);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


}
