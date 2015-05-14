package cn.kingtop.sys.dao.impl;

import java.util.List;

import cn.kingtop.sys.bean.SysMenuBean;
import cn.kingtop.sys.dao.ISysMenuDao;
import cn.kingtop.sys.model.SysMenu;

import org.kingtop.dao.BaseDao;
import org.springframework.stereotype.Repository;


@Repository
public class SysMenuDaoImpl extends BaseDao<SysMenu> implements ISysMenuDao {
	
	public SysMenuDaoImpl()	{
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenuBean> getAllMenus(String sysFlag) {
		String sql = "select menu.*,per.PERMISSION from sys_menu menu,sys_menu_permission smp,sys_permission per";
		sql += " where menu.ID=smp.MENU_ID and smp.PERMISSION_ID=per.ID";
		if(sysFlag != null && !"".equals(sysFlag))
			sql += " and menu.sys_flag='" + sysFlag + "'";
		return this.findByFreeSQL(sql, SysMenuBean.class);
	}
	
}
