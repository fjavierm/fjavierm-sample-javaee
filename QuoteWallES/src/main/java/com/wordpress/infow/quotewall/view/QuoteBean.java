package com.wordpress.infow.quotewall.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.wordpress.infow.quotewall.es.QuoteES;
import com.wordpress.infow.quotewall.es.QuoteResult;
import com.wordpress.infow.quotewall.model.Quote;
import com.wordpress.infow.quotewall.service.QuoteService;

@Named
@SessionScoped
public class QuoteBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private QuoteService quoteService;

	private String searchText;
	private List<Quote> quotes;

	private String textAdd;
	private String authorAdd;

	public QuoteBean() {
	}

	public void initQuotes() {
		QuoteResult result = this.quoteService.findAllQuotes(QuoteES.DEFAULT_PAGINATION_START, QuoteES.DEFAULT_PAGINATION_SIZE);
		if (result != null) {
			this.quotes = result.getQuotes();
		} else {
			this.quotes = new ArrayList<Quote>();
		}
	}

	public void doSearch() {
		if (this.searchText.trim().length() > 0) {
			QuoteResult result = this.quoteService.find(this.searchText, QuoteES.DEFAULT_PAGINATION_START, QuoteES.DEFAULT_PAGINATION_SIZE);
			if (result != null) {
				this.quotes = result.getQuotes();
			} else {
				this.quotes = new ArrayList<Quote>();
			}
		} else {
			this.addErrorMessage("searchError");
		}
	}

	public void reset() {
		this.searchText = "";
		QuoteResult result = this.quoteService.findAllQuotes(QuoteES.DEFAULT_PAGINATION_START, QuoteES.DEFAULT_PAGINATION_SIZE);
		if (result != null) {
			this.quotes = result.getQuotes();
		} else {
			this.quotes = new ArrayList<Quote>();
		}
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

	public String getSearchText() {
		return this.searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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
