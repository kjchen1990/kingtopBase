package org.kingtop.action;

import java.io.Serializable;

public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSucess = false;
	private Object resultObject = null;
	private String message;
	private Object params;
	
	public Result(boolean isSucess, Object resultObject) {
		super();
		this.isSucess = isSucess;
		this.resultObject = resultObject;
	}

	public Result(boolean isSucess, Object resultObject, String message) {
		super();
		this.isSucess = isSucess;
		this.resultObject = resultObject;
		this.message = message;
	}

	public Result(boolean isSucess, Object resultObject, String message, Object params) {
		super();
		this.isSucess = isSucess;
		this.resultObject = resultObject;
		this.message = message;
		this.params = params;
	}

	public boolean isSucess() {
		return isSucess;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public String getMessage() {
		return message;
	}

	public Object getParams() {
		return params;
	}
	
}
