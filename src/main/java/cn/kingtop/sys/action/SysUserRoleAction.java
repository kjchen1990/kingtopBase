package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysUserRoleService;

@Controller
public class SysUserRoleAction extends BaseAction{
	
	private ISysUserRoleService sysUserRoleService;

	public ISysUserRoleService getSysUserRoleService() {
		return sysUserRoleService;
	}

	@Autowired
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}


}