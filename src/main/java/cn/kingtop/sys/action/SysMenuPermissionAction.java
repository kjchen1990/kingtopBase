package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysMenuPermissionService;

@Controller
public class SysMenuPermissionAction extends BaseAction{
	
	private ISysMenuPermissionService sysMenuPermissionService;

	public ISysMenuPermissionService getSysMenuPermissionService() {
		return sysMenuPermissionService;
	}

	@Autowired
	public void setSysMenuPermissionService(ISysMenuPermissionService sysMenuPermissionService) {
		this.sysMenuPermissionService = sysMenuPermissionService;
	}


}