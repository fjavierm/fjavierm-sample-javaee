package com.wordpress.infow.quotewall.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {

	private String getMessage(FacesContext facesContext, String msgKey, Object... args) {
		Locale locale = facesContext.getViewRoot().getLocale();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale, classLoader);
		String msgValue = bundle.getString(msgKey);
		return MessageFormat.format(msgValue, args);
	}

	protected void addInformationMessage(String message, Object... args) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.getMessage(context, message, args), null));
	}

	protected void addWarningMessage(String message, Object... args) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, this.getMessage(context, message, args), null));
	}

	protected void addErrorMessage(String message, Object... args) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, this.getMessage(context, message, args), null));
	}
}
