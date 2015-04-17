package cn.kingtop.sys.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kingtop.action.BaseAction;
import org.kingtop.action.annotation.CurrentUser;
import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;
import org.kingtop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.kingtop.sys.model.SysUser;
import cn.kingtop.sys.service.ISysUserService;

@Controller
public class SysUserAction extends BaseAction{
	
	private ISysUserService sysUserService;

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
	@RequestMapping(value = {"index","/"})
	public String showIndex(@CurrentUser(value=Constants.CURRENT_USER)SysUser user, Model model){
		model.addAttribute("user", user);
		log.info(user.getName() + "登录成功");
		return "index";
	}

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName))//未找到帐号
			error = "用户名/密码错误";
		else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName))//错误的凭证
			error = "用户名/密码错误";
		else if(LockedAccountException.class.getName().equals(exceptionClassName))//锁定的帐号
			error = "账户被锁定";
		else if(DisabledAccountException.class.getName().equals(exceptionClassName))//禁用的帐号
			error = "帐号已经被禁用";
		else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName))//登录失败次数过多
			error = "登录失败次数过多，请稍后再试";
		else if(AuthenticationException.class.getName().equals(exceptionClassName))//验证失败
			error = "用户名/密码错误";
		else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "login";
	}
	
	@RequiresPermissions(value={"user:*"})
	@RequestMapping(value="/userlist")
	public ModelAndView userList(){
		ModelAndView modelAndView = new ModelAndView();
	//	SysUser user = (SysUser) sysUserService.getObject(1);
		//System.out.println(user.getName());
		Page<SysUser> page = sysUserService.findPage(1, 10);
		modelAndView.setViewName("userlist");
		modelAndView.addObject("page", page);
		return modelAndView;
	}


}