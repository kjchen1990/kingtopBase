package org.kingtop.lang;

public class ErrorCode {
	/**
	 * 错误代码
	 */
	private String code;
	/**
	 * 用户看到的错误信息
	 */
	private String userMessage;
	/**
	 * 开发人员开到的错误信息
	 */
	private String errorMessage;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserMessage() {
		return this.userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
