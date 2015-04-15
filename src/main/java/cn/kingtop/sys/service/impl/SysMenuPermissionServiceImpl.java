package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.kingtop.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.kingtop.sys.dao.ISysMenuPermissionDao;
import cn.kingtop.sys.model.SysMenuPermission;
import cn.kingtop.sys.service.ISysMenuPermissionService;


@Service("SysMenuPermissionService")
public class SysMenuPermissionServiceImpl extends BaseService<SysMenuPermission> implements ISysMenuPermissionService {
	private ISysMenuPermissionDao sysMenuPermissionDao;

	public ISysMenuPermissionDao getSysMenuPermissionDao() {
		return sysMenuPermissionDao;
	}

	@Autowired
	public void setSysMenuPermissionDao(ISysMenuPermissionDao sysMenuPermissionDao) {
		this.sysMenuPermissionDao = sysMenuPermissionDao;
	}

	public SysMenuPermissionServiceImpl()	{
		
	}

	

}