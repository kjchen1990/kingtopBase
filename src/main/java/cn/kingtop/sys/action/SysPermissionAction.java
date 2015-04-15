package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysPermissionService;

@Controller
public class SysPermissionAction extends BaseAction{
	
	private ISysPermissionService sysPermissionService;

	public ISysPermissionService getSysPermissionService() {
		return sysPermissionService;
	}

	@Autowired
	public void setSysPermissionService(ISysPermissionService sysPermissionService) {
		this.sysPermissionService = sysPermissionService;
	}


}