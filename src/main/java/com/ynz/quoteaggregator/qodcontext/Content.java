package com.ynz.quoteaggregator.qodcontext;

import java.util.ArrayList;
import java.util.List;

public class Content {
    private List<Quote> quotes = new ArrayList<>();
    private String copyright;

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
