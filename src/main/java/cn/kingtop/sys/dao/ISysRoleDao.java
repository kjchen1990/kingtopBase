package cn.kingtop.sys.dao;

import java.util.List;

import org.kingtop.dao.IBaseDao;

import cn.kingtop.sys.model.SysRole;

public interface ISysRoleDao extends IBaseDao<SysRole>{

	/**
	 * 通过用户id查找权限
	 * @param userId 用户id
	 * @return
	 */
	List<SysRole> findSysRoleByIds(long userId);

 
}
