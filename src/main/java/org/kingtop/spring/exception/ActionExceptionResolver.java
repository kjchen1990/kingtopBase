package org.kingtop.spring.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kingtop.action.Result;
import org.kingtop.lang.BaseException;
import org.kingtop.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ActionExceptionResolver implements HandlerExceptionResolver {
	
	protected static final Logger logger = LoggerFactory.getLogger(ActionExceptionResolver.class);

	public ActionExceptionResolver() {
	}

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String jsonString = "";
		try {
			Result result = null;
			if (ex instanceof BaseException) {
				result = new Result(false, ex.getMessage());
			} else {
				BaseException baseException = new BaseException(ex, "9003",
						logger);
				result = new Result(false, baseException.getMessage());
			}
			jsonString = JsonUtil.toString(result);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		try {
			//HandlerMethod handlerMethod = (HandlerMethod)handler;
			//ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
