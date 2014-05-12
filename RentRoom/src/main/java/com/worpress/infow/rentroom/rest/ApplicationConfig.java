package com.worpress.infow.rentroom.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath ("/rs")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();

		classes.add(UserEndPoint.class);
		classes.add(RoomEndPoint.class);
		classes.add(RentedEndPoint.class);
		classes.add(CommentEndPoint.class);

		return classes;
	}
}
