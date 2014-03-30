package com.wordpress.infow.quotewall.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the quotes database table.
 * 
 */
@Entity
@Table (name = "quotes")
@NamedQueries ({
		@NamedQuery (name = Quote.FIND_ALL, query = "SELECT q FROM Quote q"),
		@NamedQuery (name = Quote.FIND_BY_AUTHOR, query = "SELECT q FROM Quote q WHERE lower(q.author) LIKE :author"),
		@NamedQuery (name = Quote.FIND_ALL_SORTED, query = "SELECT q FROM Quote q ORDER BY q.created DESC")
})
@XmlRootElement
public class Quote implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Quote.findAll";
	public static final String FIND_BY_AUTHOR = "Quote.findByAuthor";
	public static final String FIND_ALL_SORTED = "Quote.findAllSorted";

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (unique = true, nullable = false)
	private int id;

	@Column (nullable = false, length = 30)
	private String author;

	@Column (nullable = false)
	private Timestamp created;

	@Column (nullable = false, length = 300)
	private String text;

	public Quote() {
	}

	public Quote(@NotNull String author, @NotNull String text) {
		this.author = author;
		this.text = text;
		this.created = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = (prime * result) + ((this.author == null) ? 0 : this.author.hashCode());
		result = (prime * result) + ((this.text == null) ? 0 : this.text.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Quote)) {
			return false;
		}

		Quote quote = (Quote) obj;

		if (!this.author.equals(quote.author)) {
			return false;
		}

		if (!this.text.equals(quote.text)) {
			return false;
		}

		return true;
	}
}