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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MyQuoteRepositoryTest {
    @Autowired
    private MyQuoteRepository myQuoteRepository;

    User user = new User();
    MyQuote quote = new MyQuote();

    @BeforeEach
    public void setup() {
        user.setLoginName("ynz");

        quote.setCharacter("character");
        quote.setCharacterDirection("left");
        quote.setImage("image");
        quote.setQuote("my quote");
        quote.setRating(1);
        quote.setUser(user);
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


}