package com.wordpress.infow.quotewall.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Quote implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String author;
	private Timestamp created;
	private String text;

	public Quote() {
	}

	public Quote(@NotNull String author, @NotNull String text) {
		this.id = UUID.randomUUID().toString();
		this.author = author;
		this.text = text;
		this.created = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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