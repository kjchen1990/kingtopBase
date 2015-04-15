package cn.kingtop.sys.dao;

import org.kingtop.dao.IBaseDao;

import cn.kingtop.sys.model.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser>{

	/**
	 * 通过用户名查找用户，如果查找不到，返回空
	 * @param username 用户名
	 * @return
	 */
	SysUser findByUsername(String username);

 
}
