package cn.kingtop.sys.service;

import java.util.Set;

import org.kingtop.service.IBaseService;

import cn.kingtop.sys.model.SysUser;

public interface ISysUserService extends IBaseService<SysUser>{

	/**
	 * 根据用户登录名获取用户角色字符
	 * @param username 用户登录名
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据用户登录名获取权限字符
	 * @param username 用户登录名
	 * @return
	 */
	Set<String> findPermissions(String username);

	/**
	 * 根据用户登录名获取用户信息
	 * @param username 用户登录名
	 * @return
	 */
	SysUser findByUsername(String username);

}
