package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysUserPermissionService;

@Controller
public class SysUserPermissionAction extends BaseAction{
	
	private ISysUserPermissionService sysUserPermissionService;

	public ISysUserPermissionService getSysUserPermissionService() {
		return sysUserPermissionService;
	}

	@Autowired
	public void setSysUserPermissionService(ISysUserPermissionService sysUserPermissionService) {
		this.sysUserPermissionService = sysUserPermissionService;
	}


}