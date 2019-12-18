package com.ynz.quoteaggregator.repository;

import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MyQuoteRepositoryTest {
    @Autowired
    private MyQuoteRepository myQuoteRepository;

    @Autowired
    private UserRepository userRepository;

    User user = new User();
    MyQuote quote = new MyQuote();

    User user1 = new User();

    @BeforeEach
    public void setup() {
        user.setLoginName("ynz");
        quote.setCharacter("character");
        quote.setCharacterDirection("left");
        quote.setImage("image");
        quote.setQuote("my quote");
        quote.setRating(1);
        quote.setUser(user);

        user1.setLoginName("xyz");
    }

    @Test
    @Transactional
    public void testSavingQuote() {

        MyQuote saved = myQuoteRepository.save(quote);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getUser());
    }

    @Test
    @Transactional
    public void testFindAllQuoteBack() {
        MyQuote saved = myQuoteRepository.save(quote);

        List<MyQuote> all = myQuoteRepository.findAll();
        assertEquals(1, all.size());

        MyQuote quote = all.get(0);
        assertNotNull(quote.getUser());
        assertEquals("ynz", quote.getUser().getLoginName());
    }

    @Test
    @Transactional
    public void testFindQuoteByLoginName() {
        MyQuote saved = myQuoteRepository.save(quote);

        long count = myQuoteRepository.findAll().stream().filter(q -> q.getUser().getLoginName().equals("ynz")).count();
        assertEquals(1, count);
    }

    @Test
    @Transactional
    public void whenLoginNameQuoteExited_ReturnListQuote() {
        MyQuote saved = myQuoteRepository.save(quote);
        List<MyQuote> found = myQuoteRepository.findQuoteByUserLoginName("ynz");
        assertEquals(1, found.size());
    }

    @Test
    public void whenLoginNameNotExisted_FindQuoteByLoginNameJPQL_ReturnEmptyList() {
        List<MyQuote> found = myQuoteRepository.findQuoteByUserLoginName("ynz");
        assertTrue(found.isEmpty());
    }

    @Test
    public void whenLoginNameExisted_ButHavingNoQuote_ReturnEmptyList() {
        MyQuote saved = myQuoteRepository.save(quote);
        userRepository.save(user1);

        List<MyQuote> found = myQuoteRepository.findQuoteByUserLoginName(user1.getLoginName());
        assertTrue(found.isEmpty());
    }


}