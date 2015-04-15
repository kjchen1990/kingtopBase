package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysPermissionDao;
import cn.kingtop.sys.model.SysPermission;
import cn.kingtop.sys.service.ISysPermissionService;


@Service("SysPermissionService")
public class SysPermissionServiceImpl extends BaseService<SysPermission> implements ISysPermissionService {
	private ISysPermissionDao sysPermissionDao;

	public ISysPermissionDao getSysPermissionDao() {
		return sysPermissionDao;
	}

	@Autowired
	public void setSysPermissionDao(ISysPermissionDao sysPermissionDao) {
		this.sysPermissionDao = sysPermissionDao;
	}

	public SysPermissionServiceImpl()	{
		
	}

	

}