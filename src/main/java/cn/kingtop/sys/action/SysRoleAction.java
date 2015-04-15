package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysRoleService;

@Controller
public class SysRoleAction extends BaseAction{
	
	private ISysRoleService sysRoleService;

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	@Autowired
	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}


}