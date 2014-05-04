package com.wordpress.infow.javamongodb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wordpress.infow.javamongodb.dao.MessageDAO;
import com.wordpress.infow.javamongodb.model.Message;

@WebServlet (urlPatterns = "/messageoper")
public class MessageOperations extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String INSERT = "insert";
	private static final String FIND = "find";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";

	@EJB
	private MessageDAO messageDao;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String action = request.getParameter("action");
		String language = request.getParameter("language");
		String message = request.getParameter("message");

		Message msgObj = null;

		if ((action == null) || (action.length() == 0)) {
			this.writeError(response, "Invalid action value.");
		}

		if ((language == null) || (language.length() == 0)) {
			this.writeError(response, "Invalid language parameter.");
		}

		if ((message == null) || (message.length() == 0)) {
			if (!MessageOperations.FIND.equals(action)) {
				this.writeError(response, "Invalid message parameter.");
			}
		}

		switch (action) {
			case MessageOperations.INSERT:
				msgObj = new Message(language, message);
				this.messageDao.insertMessage(msgObj);
				this.writeResult(response, "Message inserted.");
				break;

			case MessageOperations.FIND:
				msgObj = this.messageDao.findMessage(language);
				if (msgObj != null) {
					this.writeResult(response, msgObj.toString());
				} else {
					this.writeResult(response, "No messasge found.");
				}
				break;

			case MessageOperations.UPDATE:
				msgObj = this.messageDao.findMessage(language);
				if (msgObj != null) {
					this.messageDao.updateMessage(msgObj, new Message(language, message));
					this.writeResult(response, "Message updated.");
				} else {
					this.writeResult(response, "No messasge found.");
				}
				break;

			case MessageOperations.DELETE:
				msgObj = new Message(language, message);
				this.messageDao.removeMessage(msgObj);
				this.writeResult(response, "Message deleted.");
				break;

			default:
				this.writeError(response, "Invalid option");
				break;
		}
	}

	private void writeResult(HttpServletResponse response, String text) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=\"es\">");
		out.println("<head>");
		out.println("<title>Result - MongoDB Example</title>");
		out.println("</head>");
		out.println("<body>");
		try {
			out.println("<p>" + text + "</p>");
		} finally {
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	private void writeError(HttpServletResponse response, String text) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=\"es\">");
		out.println("<head>");
		out.println("<title>Result - MongoDB Example</title>");
		out.println("</head>");
		out.println("<body>");
		try {
			out.println("<p>" + text + "</p>");
		} finally {
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Messasge DB Operations.";
	}
}
