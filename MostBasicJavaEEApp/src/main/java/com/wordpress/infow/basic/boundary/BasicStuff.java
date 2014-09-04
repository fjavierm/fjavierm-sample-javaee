package com.wordpress.infow.basic.boundary;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.wordpress.infow.basic.entity.BasicEntity;

@Stateless
public class BasicStuff {

	@Inject
	BasicEntity be;

	public String getDate() {
		return new Date().toString() + " - " + this.be.getStatus();
	}
}
