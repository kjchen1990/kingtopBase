package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysRolePermissionService;

@Controller
public class SysRolePermissionAction extends BaseAction{
	
	private ISysRolePermissionService sysRolePermissionService;

	public ISysRolePermissionService getSysRolePermissionService() {
		return sysRolePermissionService;
	}

	@Autowired
	public void setSysRolePermissionService(ISysRolePermissionService sysRolePermissionService) {
		this.sysRolePermissionService = sysRolePermissionService;
	}


}