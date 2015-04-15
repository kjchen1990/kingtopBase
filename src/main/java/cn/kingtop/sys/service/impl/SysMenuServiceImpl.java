package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysMenuDao;
import cn.kingtop.sys.model.SysMenu;
import cn.kingtop.sys.service.ISysMenuService;


@Service("SysMenuService")
public class SysMenuServiceImpl extends BaseService<SysMenu> implements ISysMenuService {
	private ISysMenuDao sysMenuDao;

	public ISysMenuDao getSysMenuDao() {
		return sysMenuDao;
	}

	@Autowired
	public void setSysMenuDao(ISysMenuDao sysMenuDao) {
		this.sysMenuDao = sysMenuDao;
	}

	public SysMenuServiceImpl()	{
		
	}

	

}