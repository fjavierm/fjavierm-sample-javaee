package com.wordpress.infow.quotewallnosql.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.wordpress.infow.quotewallnosql.model.Quote;
import com.wordpress.infow.quotewallnosql.services.QuoteService;

@Named
@SessionScoped
public class QuoteBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private QuoteService quoteService;

	private String author;
	private List<Quote> quotes;

	private String textAdd;
	private String authorAdd;

	public QuoteBean() {
	}

	public void initQuotes() {
		this.quotes = this.quoteService.findAllQuotesSorted();
	}

	public void doSearch() {
		if (this.author.trim().length() > 0) {
			this.quotes = this.quoteService.findByAuthor(this.author);
		} else {
			this.addErrorMessage("searchError");
		}
	}

	public void reset() {
		this.author = "";
		this.quotes = this.quoteService.findAllQuotesSorted();
	}

	public String add() {
		if ((this.authorAdd.trim().length() > 0) && (this.textAdd.trim().length() > 0)) {
			this.quoteService.createQuote(new Quote(this.authorAdd, this.textAdd));
			this.initQuotes();
			this.authorAdd = "";
			this.textAdd = "";
		} else {
			this.addErrorMessage("addError");
		}

		return "";
	}

	public List<Quote> getQuotes() {
		if (this.quotes == null) {
			this.initQuotes();
		}

		return this.quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTextAdd() {
		return this.textAdd;
	}

	public void setTextAdd(String textAdd) {
		this.textAdd = textAdd;
	}

	public String getAuthorAdd() {
		return this.authorAdd;
	}

	public void setAuthorAdd(String authorAdd) {
		this.authorAdd = authorAdd;
	}

}
