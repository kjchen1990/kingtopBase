package org.kingtop.action;

import org.kingtop.lang.BaseException;
import org.kingtop.sys.User;
import org.kingtop.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseAction {
	public final Logger log = LoggerFactory.getLogger(getClass());

	/*private ILogService logService;

	public ILogService getLogService() {
		return logService;
	}

	@Autowired
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}*/

	protected String getJson(boolean isSucess, Object object)
			throws BaseException {
		try {
			Result result = new Result(isSucess, object);
			return JsonUtil.toString(result);
		} catch (Exception e) {
			throw new BaseException(e, "9004", this.log);
		}
	}

	protected String getJsonAsNumberNull(boolean isSucess, Object object)
			throws BaseException {
		try {
			Result result = new Result(isSucess, object);
			return JsonUtil.toStringAsNull(result);
		} catch (Exception e) {
			throw new BaseException(e, "9004", this.log);
		}
	}

	protected String getCloseWindowJavaScript() {
		return "<script language='javascript'>parent.$.closeWindow();parent.location.reload();</script>";
	}

	protected String getGoBackJavaScript() {
		return "<script language='javascript'>history.go(-2);</script>";
	}

	protected void addLog(User user, String moduleName, String content,
			String meno) {
		/*logService = (ILogService) SpringUtils.getBean("logSerivce");
		try {
			logService.addLog(user, moduleName, content, meno);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}*/
	}
}
