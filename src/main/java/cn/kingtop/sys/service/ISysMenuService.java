package cn.kingtop.sys.service;

import java.util.List;

import org.kingtop.service.IBaseService;

import cn.kingtop.sys.bean.SysMenuBean;
import cn.kingtop.sys.model.SysMenu;

public interface ISysMenuService extends IBaseService<SysMenu>{

	/**
	 * 查询指定用户的菜单
	 * @param userId 用户id
	 * @param sysFlag 系统标识，如果为空，则查全部
	 * @return
	 */
	public List<Object> getUserMenus(long userId,String sysFlag);
	
}
