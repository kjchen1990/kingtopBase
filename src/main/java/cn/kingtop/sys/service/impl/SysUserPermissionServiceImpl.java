package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysUserPermissionDao;
import cn.kingtop.sys.model.SysUserPermission;
import cn.kingtop.sys.service.ISysUserPermissionService;


@Service("SysUserPermissionService")
public class SysUserPermissionServiceImpl extends BaseService<SysUserPermission> implements ISysUserPermissionService {
	private ISysUserPermissionDao sysUserPermissionDao;

	public ISysUserPermissionDao getSysUserPermissionDao() {
		return sysUserPermissionDao;
	}

	@Autowired
	public void setSysUserPermissionDao(ISysUserPermissionDao sysUserPermissionDao) {
		this.sysUserPermissionDao = sysUserPermissionDao;
	}

	public SysUserPermissionServiceImpl()	{
		
	}

	

}