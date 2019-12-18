package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User user = new User();
    User user1 = new User();

    @BeforeEach
    public void setup() {
        user.setLoginName("ynz");

        user1.setLoginName("xwy");
    }

    @Test
    @Transactional
    void testSaveUser() {
        User saved = userRepository.save(user);
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindUserByLoginName() {
        userRepository.save(user);
        userRepository.save(user1);
        User found = userRepository.findUserByLoginName(user.getLoginName());
        assertEquals(user.getId(), found.getId());
    }

    @Test
    void testFindUserNotExisted() {
        User found = userRepository.findUserByLoginName("ynz");
        assertNull(found);
    }
}