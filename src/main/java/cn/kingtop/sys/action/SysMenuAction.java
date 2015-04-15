package cn.kingtop.sys.action;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kingtop.sys.service.ISysMenuService;

@Controller
public class SysMenuAction extends BaseAction{
	
	private ISysMenuService sysMenuService;

	public ISysMenuService getSysMenuService() {
		return sysMenuService;
	}

	@Autowired
	public void setSysMenuService(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}


}