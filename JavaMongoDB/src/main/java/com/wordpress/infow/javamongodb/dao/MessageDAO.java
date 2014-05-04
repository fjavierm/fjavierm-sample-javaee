package com.wordpress.infow.javamongodb.dao;

import javax.ejb.Stateless;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.wordpress.infow.javamongodb.db.MongoConnection;
import com.wordpress.infow.javamongodb.model.Message;

@Stateless
public class MessageDAO {

	private MongoConnection conn;

	public void insertMessage(Message message) {
		this.conn = MongoConnection.getInstance();

		this.conn.getCollection().insert(message.getBasicDBObject());
	}

	public Message findMessage(String language) {
		Message message = null;

		this.conn = MongoConnection.getInstance();
		BasicDBObject query = new BasicDBObject();
		query.put("language", language);
		DBObject obj = this.conn.getCollection().findOne(query);
		if (obj != null) {
			message = new Message(obj);
		}

		return message;
	}

	public void updateMessage(Message oldMessage, Message newMessage) {
		this.conn = MongoConnection.getInstance();

		this.conn.getCollection().update(oldMessage.getBasicDBObject(), newMessage.getBasicDBObject());
	}

	public void removeMessage(Message message) {
		this.conn = MongoConnection.getInstance();

		this.conn.getCollection().remove(message.getBasicDBObject());
	}

	public long getDocumentsNumber() {
		this.conn = MongoConnection.getInstance();
		return this.conn.getCollection().getCount();
	}
}
