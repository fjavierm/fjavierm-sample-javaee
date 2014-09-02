package com.wordpress.infow.quotewall.es;

import java.util.List;

import com.wordpress.infow.quotewall.model.Quote;

public class QuoteResult {

	private List<Quote> quotes;
	private long totalQuotes;

	public List<Quote> getQuotes() {
		return this.quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public long getTotalQuotes() {
		return this.totalQuotes;
	}

	public void setTotalQuotes(long totalQuotes) {
		this.totalQuotes = totalQuotes;
	}

}
