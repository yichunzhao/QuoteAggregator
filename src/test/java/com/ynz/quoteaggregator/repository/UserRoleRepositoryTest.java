package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.domainmodel.Role;
import com.ynz.quoteaggregator.entities.User;
import com.ynz.quoteaggregator.entities.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRoleRepositoryTest {
    @Autowired
    private UserRoleRepository repository;

    UserRole userRole = new UserRole();
    User user = new User();

    @BeforeEach
    public void setup() {
        user.setLoginName("ynz");
        userRole.setRole(Role.USER);
        userRole.setUser(user);
    }

    @Test
    @Transactional
    public void testSavingUserRole() {
        UserRole saved = repository.save(userRole);
        assertNotNull(saved);

        assertEquals(user.getLoginName(), saved.getUser().getLoginName());
        assertEquals(Role.USER, saved.getRole());
    }

    @Test
    @Transactional
    public void testFindUserRolesByLoginName(){
        UserRole saved = repository.save(userRole);

        Set<UserRole> found = repository.findUserRolesByLoginName("ynz");
        assertNotNull(found);
        assertEquals(1, found.size());
    }

    @Test
    @Transactional
    public void whenLoginNameNotExisted_ReturnEmptyCollection(){
        Set<UserRole> found = repository.findUserRolesByLoginName("what");
        assertTrue(found.isEmpty());
    }

}