package cn.kingtop.sys.dao;

import java.util.List;

import org.kingtop.dao.IBaseDao;

import cn.kingtop.sys.bean.SysMenuBean;
import cn.kingtop.sys.model.SysMenu;

public interface ISysMenuDao extends IBaseDao<SysMenu>{

	/**
	 * 获取系统所有菜单，包含权限字符
	 * @param sysFlag 系统标识，如果为空，则查全部
	 * @return
	 */
	public List<SysMenuBean> getAllMenus(String sysFlag);
 
}
