package com.ynz.quoteaggregator.dto;

import com.ynz.quoteaggregator.entities.MyQuote;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class MyQuoteDto implements Serializable {

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

    public MyQuoteDto() {
    }

    public MyQuoteDto(MyQuote myQuote) {
        setCharacter(myQuote.getCharacter());
        setCharacterDirection(myQuote.getCharacterDirection());
        setImage(myQuote.getImage());
        setQuote(myQuote.getQuote());
        setRating(myQuote.getRating());
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


    public MyQuote toModel() {
        MyQuote myQuote = new MyQuote();
        myQuote.setRating(this.rating);
        myQuote.setQuote(this.quote);
        myQuote.setImage(this.image);
        myQuote.setCharacterDirection(this.characterDirection);
        myQuote.setCharacter(this.character);
        return myQuote;
    }
}
