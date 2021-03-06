package cn.kingtop.sys.dao;

import java.util.List;

import org.kingtop.dao.IBaseDao;

import cn.kingtop.sys.model.SysPermission;

public interface ISysPermissionDao extends IBaseDao<SysPermission>{

	/**
	 * 通过用户id查询权限
	 * @param userId 用户id
	 * @return
	 */
	List<SysPermission> findPermissionByUserIdRoleIds(long userId);

 
}
