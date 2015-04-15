package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysUserRoleDao;
import cn.kingtop.sys.model.SysUserRole;
import cn.kingtop.sys.service.ISysUserRoleService;


@Service("SysUserRoleService")
public class SysUserRoleServiceImpl extends BaseService<SysUserRole> implements ISysUserRoleService {
	private ISysUserRoleDao sysUserRoleDao;

	public ISysUserRoleDao getSysUserRoleDao() {
		return sysUserRoleDao;
	}

	@Autowired
	public void setSysUserRoleDao(ISysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public SysUserRoleServiceImpl()	{
		
	}

	

}