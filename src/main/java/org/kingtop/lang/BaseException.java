package org.kingtop.lang;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseException extends RuntimeException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6410601541422116286L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private Exception exception;
	private Logger log;
	public String userMessage = "";
	public String code = "";
	private String errorMessage = "";
	/**
	 * 参数
	 */
	private Object keyObject = null;
	private String exceptionClass = "";
	/**
	 * 所有错误信息
	 */
	private String message = null;
	
	/**
	 * 根据异常初始化类
	 * @param exception 异常
	 * @param log 日志记录
	 */
	public BaseException(Exception exception, Logger log) {
		initFormException(exception,log,null);
	}
	
	/**
	 * 根据异常初始化类
	 * @param exception 异常
	 * @param log 日志记录
	 * @param keyObject 参数
	 */
	public BaseException(Exception exception, Logger log, Object keyObject){
		initFormException(exception,log,keyObject);
	}
	
	/**
	 * 根据错误代码获取错误信息
	 * @param code 错误代码
	 * @param log 日志记录器
	 */
	public BaseException(String code, Logger log) {
		this.initFromCode(code, log, null, null, null);
	}
	
	/**
	 * 根据错误代码获取错误信息，也可以直接指定错误信息
	 * @param code 错误代码
	 * @param log 日志记录器
	 * @param errorMessage 系统错误信息
	 * @param userMessage 用户错误信息
	 */
	public BaseException(String code, Logger log, String errorMessage, String userMessage) {
		this.initFromCode(code, log, errorMessage, userMessage, null);
	}
	
	/**
	 * 根据错误代码获取错误信息，也可以直接指定错误信息
	 * @param code 错误代码
	 * @param log 日志记录器
	 * @param errorMessage 系统错误信息
	 * @param userMessage 用户错误信息
	 * @param keyObject 参数
	 */
	public BaseException(String code, Logger log, String errorMessage, String userMessage, Object keyObject) {
		this.initFromCode(code, log, errorMessage, userMessage, keyObject);
	}
	
	/**
	 * 根据异常和错误代码生存错误信息
	 * @param exception
	 * @param code
	 * @param log
	 */
	public BaseException(Exception exception, String code, Logger log){
		this.intFormExcetionAndCode(exception, code, log, null, null, null);
	}
	
	/**
	 * 根据异常初始化类
	 * @param exception 异常
	 * @param log 日志记录
	 * @param keyObject 参数
	 */
	protected void initFormException(Exception exception, Logger log, Object keyObject){
		if(exception == null)
			return ;
		this.exception = exception;
		this.log = log;
		this.keyObject = keyObject;
		
		if ((this.exception instanceof BaseException)) {
			this.code = ((BaseException)this.exception).code;
			this.userMessage = ((BaseException)this.exception).userMessage;
		}else {
			this.exceptionClass = this.exception.getClass().getName();
			ErrorCode errorCode = (ErrorCode) ExceptionConfig.systemMap.get(exceptionClass);
			if (errorCode == null) {
				errorCode = (ErrorCode) ExceptionConfig.systemMap.get("default");
			}
			this.code = errorCode.getCode();
			this.userMessage = errorCode.getUserMessage();
			this.errorMessage = errorCode.getErrorMessage();
			printSystemErrorLog();
		}
	}
	
	/**
	 * 根据错误代码获取错误信息，也可以直接指定错误信息
	 * @param code 错误代码
	 * @param log 日志记录器
	 * @param errorMessage 系统错误信息
	 * @param userMessage 用户错误信息
	 * @param keyObject 参数
	 */
	protected void initFromCode(String code, Logger log, String errorMessage, String userMessage, Object keyObject){
		this.log = log;
		this.code = code;
		this.errorMessage = errorMessage;
		this.userMessage = userMessage;
		this.keyObject = keyObject;
		ErrorCode errorCode = (ErrorCode) ExceptionConfig.applyMap.get(this.code);
		if (errorCode == null) {
			errorCode = (ErrorCode) ExceptionConfig.applyMap.get("000000");
		}
		if(this.userMessage == null || "".equals(this.userMessage.trim()))
			this.userMessage = errorCode.getUserMessage();
		if(this.errorMessage == null || "".equals(this.errorMessage.trim()))
			this.errorMessage = errorCode.getErrorMessage();
		printSystemErrorLog();
	}
	
	/**
	 * 根据异常和错误代码生成错误信息，可以自定义错误信息
	 * @param exception 异常
	 * @param code 错误代码
	 * @param log 日志
	 * @param keyObject 参数
	 * @param errorMessage 系统错误信息
	 * @param userMessage 用户错误信息
	 */
	protected void intFormExcetionAndCode(Exception exception, String code, Logger log, Object keyObject, String errorMessage, String userMessage){
		this.log = log;
		this.code = code;
		this.errorMessage = errorMessage;
		this.userMessage = userMessage;
		this.exception = exception;
		this.keyObject = keyObject;
		
		if(this.exception == null)
			this.initFromCode(code, log, errorMessage, userMessage, keyObject);
		else{
			if ((exception instanceof BaseException)) {
				this.code = code;
				if(this.code == null || "".equals(code.trim()))
					this.code = ((BaseException)this.exception).code;
				this.userMessage = ((BaseException)this.exception).userMessage;
			}else {
				if(this.exception.getCause() != null)
					this.exceptionClass = this.exception.getCause().getClass().getName();
				else
					this.exceptionClass = this.exception.getClass().getName();
				ErrorCode errorCode = (ErrorCode) ExceptionConfig.systemMap.get(exceptionClass);
				if (errorCode == null) {
					errorCode = (ErrorCode) ExceptionConfig.systemMap.get("default");
				}
				this.code = errorCode.getCode();
				this.userMessage = errorCode.getUserMessage();
				this.errorMessage = errorCode.getErrorMessage();
				if(userMessage != null && !"".equals(userMessage))
					this.userMessage = userMessage;
				if(errorMessage != null && !"".equals(errorMessage))
					this.errorMessage = errorMessage;
				printSystemErrorLog();
			}
		}
	}
	
	/**
	 * 将系统错误输出到日志中
	 */
	protected void printSystemErrorLog(){
		try {
			String exceptionStackTraceInfo = getExceptionStackTraceInfo();

			StackTraceElement stackTraceElement = getStackTrace()[0];
			String errorInfo = "";
			if (this.keyObject != null) {
				errorInfo = "类名[" + stackTraceElement.getClassName() + "],方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "],关键数据[{}]";
				this.log.error(errorInfo + exceptionStackTraceInfo, this.keyObject);
			}
			else {
				errorInfo = "类名[" + stackTraceElement.getClassName() + "],方法[" + stackTraceElement.getMethodName() + "],代码[" + this.code + "],异常[" + this.errorMessage + "]";
				this.log.error(errorInfo + exceptionStackTraceInfo);
			}
		}
		catch (Exception e) {
			this.logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取错误信息和错误代码
	 */
	public String getMessage() {
		if (this.message == null || "".equals(this.message)) {
			this.message = (this.userMessage + ",代码[" + this.code + "],请与管理员联系");
		}
		return this.message;
	}

	public String getErrorCode() {
		return this.code;
	}

	/**
	 * 获取exception中的错误信息
	 * @return
	 */
	private String getExceptionStackTraceInfo() {
		String exceptionTrace = "";
		if(this.exception == null)
			return exceptionTrace;
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
}
