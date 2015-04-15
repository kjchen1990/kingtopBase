package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysRolePermissionDao;
import cn.kingtop.sys.model.SysRolePermission;
import cn.kingtop.sys.service.ISysRolePermissionService;


@Service("SysRolePermissionService")
public class SysRolePermissionServiceImpl extends BaseService<SysRolePermission> implements ISysRolePermissionService {
	private ISysRolePermissionDao sysRolePermissionDao;

	public ISysRolePermissionDao getSysRolePermissionDao() {
		return sysRolePermissionDao;
	}

	@Autowired
	public void setSysRolePermissionDao(ISysRolePermissionDao sysRolePermissionDao) {
		this.sysRolePermissionDao = sysRolePermissionDao;
	}

	public SysRolePermissionServiceImpl()	{
		
	}

	

}