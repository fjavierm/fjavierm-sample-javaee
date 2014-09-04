package com.wordpress.infow.basic.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordpress.infow.basic.boundary.BasicStuff;

@Path("/sessions")
public class Session {

	@Inject
	BasicStuff bs;

	@GET
	public String getSession() {
		return "Jonh Doe" + this.bs.getDate();
	}
}
