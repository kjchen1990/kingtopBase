package cn.kingtop.sys.action;

import java.util.List;

import org.kingtop.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/**
	 * 查询指定用户的菜单
	 * @param userId 用户id
	 * @param sysFlag 系统标识，如果为空，则查全部
	 * @return
	 */
	@RequestMapping("getUserMenus")
	@ResponseBody
	public String getUserMenus(long userId,String sysFlag){
		List<Object> list = this.sysMenuService.getUserMenus(userId, sysFlag);
		return this.getJson(true, list);
	}

}