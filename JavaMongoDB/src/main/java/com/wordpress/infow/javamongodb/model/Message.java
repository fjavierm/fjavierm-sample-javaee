package com.wordpress.infow.javamongodb.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Message {

	private String language;
	private String message;

	public Message(String language, String message) {
		this.language = language;
		this.message = message;
	}

	public Message(DBObject obj) {
		this.language = (String) obj.get("language");
		this.message = (String) obj.get("message");
	}

	public BasicDBObject getBasicDBObject() {
		BasicDBObject doc = new BasicDBObject();
		doc.put("language", this.getLanguage());
		doc.put("message", this.getMessage());

		return doc;
	}

	@Override
	public String toString() {
		return String.format("Language: %s, Message: %s", (this.language != null ? this.language : ""),
				(this.message != null ? this.message : ""));
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
