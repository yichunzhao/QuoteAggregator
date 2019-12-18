package com.ynz.quoteaggregator.simpsonscontext;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class MultipleQuotes {
    @JsonIgnore
    private List<QuoteContainer> quotes = new ArrayList<>();

}
