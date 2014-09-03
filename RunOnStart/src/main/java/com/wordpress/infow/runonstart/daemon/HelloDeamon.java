package com.wordpress.infow.runonstart.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.wordrepss.infow.runonstart.job.HelloJob;

public class HelloDeamon extends HttpServlet {

	private static final long serialVersionUID = -4222101659367859327L;

	private ExecutorService executor;

	public HelloDeamon() {

	}

	@Override
	public void init() throws ServletException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable c = new HelloJob();
		executor.execute(c);
	}

	@Override
	public void destroy() {
		this.executor.shutdown();
		while (!this.executor.isTerminated()) {
		}
	}
}
