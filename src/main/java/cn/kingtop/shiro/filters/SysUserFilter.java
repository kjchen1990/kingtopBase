package cn.kingtop.shiro.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.kingtop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import cn.kingtop.sys.model.SysUser;
import cn.kingtop.sys.service.ISysUserService;

/**
 * 用于判断action中方法的属性是否有加@CurrentUser，有的话，为之注入一个User
 * @author Administrator
 * 
 */
public class SysUserFilter extends PathMatchingFilter {

	private ISysUserService userService;

	public ISysUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(ISysUserService userService) {
		this.userService = userService;
	}

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SysUser sysUser = userService.findByUsername(username);
		if (sysUser != null) {
			request.setAttribute(Constants.CURRENT_USER, sysUser);
		}
		return true;
	}

	/**
	 * 获取客户端IP地址
	 */
	protected String getIpAddr(HttpServletRequest request) {

		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
