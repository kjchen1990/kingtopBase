package cn.kingtop.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kingtop.lang.BaseException;
import org.kingtop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.shiro.annotation.AuthCacheAnnotation;
import cn.kingtop.sys.dao.ISysPermissionDao;
import cn.kingtop.sys.dao.ISysRoleDao;
import cn.kingtop.sys.dao.ISysUserDao;
import cn.kingtop.sys.model.SysPermission;
import cn.kingtop.sys.model.SysRole;
import cn.kingtop.sys.model.SysUser;
import cn.kingtop.sys.service.ISysUserService;


@Service("SysUserService")
public class SysUserServiceImpl extends BaseService<SysUser> implements ISysUserService {
	private ISysUserDao sysUserDao;

	public ISysUserDao getSysUserDao() {
		return sysUserDao;
	}

	@Autowired
	public void setSysUserDao(ISysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	private ISysRoleDao sysRoleDao;
	
	public ISysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}
	@Autowired
	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public ISysPermissionDao getSysPermissionDao() {
		return sysPermissionDao;
	}
	@Autowired
	public void setSysPermissionDao(ISysPermissionDao sysPermissionDao) {
		this.sysPermissionDao = sysPermissionDao;
	}

	private ISysPermissionDao sysPermissionDao;

	public SysUserServiceImpl()	{
		
	}

	@Override
	public Set<String> findRoles(String username) {
		SysUser user = findByUsername(username);
		if(user != null){
			List<SysRole> list = this.sysRoleDao.findSysRoleByIds(user.getId());
			Set<String> set = new HashSet<String>();
			for (SysRole role : list) {
				set.add(role.getRole());
			}
			return set;
		}
		return null;
	}

	@Override
	public Set<String> findPermissions(String username) {
		SysUser user = findByUsername(username);
		if(user != null){
			List<SysRole> list = this.sysRoleDao.findSysRoleByIds(user.getId());
			StringBuffer roleIds = new StringBuffer();
			for (SysRole sysRole : list) {
				if(!"".equals(roleIds.toString()))
					roleIds.append(",");
				roleIds.append(sysRole.getId());
			}
			List<SysPermission> permissionList = sysPermissionDao.findPermissionByUserIdRoleIds(user.getId(), roleIds.toString());
			Set<String> set = new HashSet<String>();
			for (SysPermission sysPermission : permissionList) {
				set.add(sysPermission.getPermission());
			}
			return set;
		}
		return null;
	}

	@AuthCacheAnnotation
	@Override
	public SysUser findByUsername(String username) {
		try {
			if (username != null) {
				String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(username);
				username = m.replaceAll("").trim();
				SysUser user = this.sysUserDao.findByUsername(username);
				/*if(user != null)
					passwordHelper.encryptPassword(user);*/
				return user;
			}
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, log);
		}
	}

	

}