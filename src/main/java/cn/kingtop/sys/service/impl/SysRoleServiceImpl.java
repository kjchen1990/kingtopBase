package cn.kingtop.sys.service.impl;

import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.dao.ISysRoleDao;
import cn.kingtop.sys.model.SysRole;
import cn.kingtop.sys.service.ISysRoleService;


@Service("SysRoleService")
public class SysRoleServiceImpl extends BaseService<SysRole> implements ISysRoleService {
	private ISysRoleDao sysRoleDao;

	public ISysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	@Autowired
	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public SysRoleServiceImpl()	{
		
	}

	

}