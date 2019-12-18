package com.ynz.quoteaggregator.entities;

import com.ynz.quoteaggregator.dto.MyQuoteDto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "My_Quotes")
public class MyQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "my quote must have a quote statement.")
    private String quote;

    @NotBlank(message = "my quote must have a character.")
    private String character;

    @NotBlank(message = "my quote must have a image.")
    private String image;

    @NotBlank(message = "my quote must have a characterDirection")
    private String characterDirection;

    @Min(0)
    @Max(5)
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_TO_USER")
    @NotNull
    private User user;

    public MyQuote() {
    }

    public MyQuote(MyQuoteDto myQuoteDto) {
        if (myQuoteDto == null) throw new IllegalArgumentException("myQuoteDto should not be null");
        setCharacter(myQuoteDto.getCharacter());
        setCharacterDirection(myQuoteDto.getCharacterDirection());
        setImage(myQuoteDto.getImage());
        setQuote(myQuoteDto.getQuote());
        setRating(myQuoteDto.getRating());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCharacterDirection() {
        return characterDirection;
    }

    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
