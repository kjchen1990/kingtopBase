package org.kingtop.lang;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

public class BaseException extends RuntimeException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6410601541422116286L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private Exception exception;
	private Logger log;
	private TYPE exceptionType;
	private String userMessage = "";
	private String code = "";
	private String errorMessage = "";
	private Object keyObject = null;
	private boolean isExistKeyObject = false;
	private String message = null;

	public BaseException(Exception exception, Logger log) {
		this.exception = exception;
		this.log = log;

		if ((exception instanceof BaseException)) {
			formatBaseException();
			writeTraceErrorLog();
		}
		else if ((exception instanceof DataAccessException)) {
			formatDatabaseException();
			writeSysErrorLog();
		}
		else {
			formatSystemException();
			writeSysErrorLog();
		}
	}

	public BaseException(Exception exception, Logger log, Object keyObject) {
		this.exception = exception;
		this.log = log;
		this.keyObject = keyObject;
		this.isExistKeyObject = true;

		if ((exception instanceof BaseException)) {
			formatBaseException();
			writeTraceErrorLog();
		}
		else if ((exception instanceof DataAccessException)) {
			formatDatabaseException();
			writeSysErrorLog();
		}
		else {
			formatSystemException();
			writeSysErrorLog();
		}
	}

	public BaseException(String code, Logger log) {
		this.code = code;
		this.log = log;
		formatApplyException();
		writeAppErrorLog();
	}

	public BaseException(Exception exception, String code, Logger log) {
		this.exception = exception;
		this.code = code;
		this.log = log;
		if ((exception instanceof BaseException)) {
			formatBaseException();
			writeTraceErrorLog();
		}
		else {
			formatApplyException();
			writeSysErrorLog();
		}
	}

	public BaseException(String code, Logger log, Object keyObject) {
		this.code = code;
		this.log = log;
		this.keyObject = keyObject;
		this.isExistKeyObject = true;
		formatApplyException();
		writeAppErrorLog();
	}

	public BaseException(Exception exception, String code, Logger log, Object keyObject) {
		this.exception = exception;
		this.code = code;
		this.log = log;
		this.keyObject = keyObject;
		this.isExistKeyObject = true;
		if ((exception instanceof BaseException)) {
			formatBaseException();
			writeTraceErrorLog();
		}
		else {
			formatApplyException();
			writeSysErrorLog();
		}
	}

	public BaseException(String message) {
		this.message = message;
		this.exceptionType = TYPE.BIZ;
	}

	/**
	 * 获取异常信息
	 */
	private void formatBaseException() {
		this.message = this.exception.getMessage();
	}

	public void formatBaseException(Throwable t) {
		this.message = t.getMessage();
	}

	private void formatDatabaseException() {
		try {
			this.exceptionType = TYPE.DATABASE;

			String exceptionClass = this.exception.getCause().getClass().getName();

			ErrorCode errorCode = (ErrorCode) ExceptionConfig.databaseMap.get(exceptionClass);
			if (errorCode == null) {
				errorCode = (ErrorCode) ExceptionConfig.databaseMap.get("default");
			}
			this.code = errorCode.getCode();
			this.userMessage = errorCode.getUserMessage();
			this.errorMessage = errorCode.getErrorMessage();
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void formatRuleException() {
		try {
			this.exceptionType = TYPE.RULE;
			Throwable cause = this.exception.getCause();
			if ((cause instanceof BaseException)) {
				formatBaseException(cause);
			}
			else {
				String exceptionClass = cause.getClass().getName();

				ErrorCode errorCode = (ErrorCode) ExceptionConfig.ruleMap.get(exceptionClass);
				if (errorCode == null) {
					errorCode = (ErrorCode) ExceptionConfig.ruleMap.get("default");
				}
				this.code = errorCode.getCode();
				this.userMessage = errorCode.getUserMessage();
				this.errorMessage = errorCode.getErrorMessage();
			}
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void formatSystemException() {
		try {
			this.exceptionType = TYPE.SYSTEM;

			String exceptionClass = this.exception.getClass().getName();

			ErrorCode errorCode = (ErrorCode) ExceptionConfig.systemMap.get(exceptionClass);
			if (errorCode == null) {
				errorCode = (ErrorCode) ExceptionConfig.systemMap.get("default");
			}
			this.code = errorCode.getCode();
			this.userMessage = errorCode.getUserMessage();
			this.errorMessage = errorCode.getErrorMessage();
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void formatApplyException() {
		try {
			this.exceptionType = TYPE.APPLY;
			ErrorCode errorCode = (ErrorCode) ExceptionConfig.applyMap.get(this.code);
			if (errorCode == null) {
				errorCode = (ErrorCode) ExceptionConfig.applyMap.get("000000");
			}
			this.userMessage = errorCode.getUserMessage();
			this.errorMessage = errorCode.getErrorMessage();
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void writeSysErrorLog() {
		try {
			String exceptionStackTraceInfo = getExceptionStackTraceInfo();

			StackTraceElement stackTraceElement = getStackTrace()[0];
			String errorInfo = "";
			if (this.isExistKeyObject) {
				errorInfo = "方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "],关键数据[{}]";
				this.log.error(errorInfo + exceptionStackTraceInfo, this.keyObject);
			}
			else {
				errorInfo = "方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "]";

				this.log.error(errorInfo + exceptionStackTraceInfo);
			}
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void writeAppErrorLog() {
		try {
			StackTraceElement stackTraceElement = getStackTrace()[0];
			String errorInfo = "";
			if (this.isExistKeyObject) {
				errorInfo = "方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "],关键数据[{}]";
				this.log.error(errorInfo, this.keyObject);
			}
			else {
				errorInfo = "方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "]";
				this.log.error(errorInfo);
			}
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	/**
	 * 将出发异常的方法输出
	 */
	private void writeTraceErrorLog() {
		try {
			StackTraceElement stackTraceElement = getStackTrace()[0];
			String errorInfo = "方法[" + stackTraceElement.getMethodName() + "]";
			this.log.error(errorInfo);
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	public String getMessage() {
		if (this.message == null) {
			this.message = (this.userMessage + "[" + this.code + "],请与管理员联系");
		}
		return this.message;
	}

	public String getErrorCode() {
		return this.code;
	}

	public TYPE getExceptionType() {
		return this.exceptionType;
	}

	private String getExceptionStackTraceInfo() {
		String exceptionTrace = "";
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			this.exception.printStackTrace(pw);
			exceptionTrace = "\r\n" + sw.toString();
			pw.flush();
			pw.close();
			sw.flush();
			sw.close();
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return exceptionTrace;
	}

	static enum TYPE {
		DATABASE, SYSTEM, APPLY, BIZ, RULE;
	}
}
