package com.ynz.quoteaggregator.entities;

import com.ynz.quoteaggregator.domainmodel.Role;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USER_ROLES")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ROLE")
    @Enumerated
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_To_USER")
    private User user;

    public UserRole() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
