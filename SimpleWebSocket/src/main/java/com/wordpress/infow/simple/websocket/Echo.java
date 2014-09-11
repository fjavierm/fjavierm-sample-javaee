package com.wordpress.infow.simple.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class Echo {

	private Session session;

	@OnOpen
	public void connect(Session session) {
		this.session = session;
		System.out.println("Session = " + session);
	}

	@OnClose
	public void close() {
		this.session = null;
		System.out.println("Closed!");
	}

	@OnMessage
	public void onMessage(String msg) {
		System.out.println("msg = " + msg);
		// If you use asyncRemote, you don't need to catch exceptions
		this.session.getAsyncRemote().sendText("Echo: " + msg);
	}
}
