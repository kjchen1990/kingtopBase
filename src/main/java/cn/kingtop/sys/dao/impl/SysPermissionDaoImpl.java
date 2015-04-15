package cn.kingtop.sys.dao.impl;

import java.util.List;

import cn.kingtop.sys.dao.ISysPermissionDao;
import cn.kingtop.sys.model.SysPermission;

import org.kingtop.dao.BaseDao;
import org.springframework.stereotype.Repository;


@Repository
public class SysPermissionDaoImpl extends BaseDao<SysPermission> implements ISysPermissionDao {
	
	public SysPermissionDaoImpl()	{
		
	}

	@Override
	public List<SysPermission> findPermissionByUserIdRoleIds(long userId, String roleIds) {
		StringBuffer sql = new StringBuffer();
		sql.append("select p.* from sys_user_permission up,sys_permission p");
		sql.append(" where up.permission_id=p.id");
		sql.append(" and up.user_id= " + userId);
		if(roleIds != null && !"".equals(roleIds)){
			sql.append(" union ");
			sql.append("select p.* from sys_role_permission rp, sys_permission p");
			sql.append(" where rp.permission_id=p.id and p.available=1");
			if(roleIds.indexOf(",") > -1)
				sql.append(" and rp.role_id in (" + roleIds + ")");
			else
				sql.append(" and rp.role_id =" + roleIds);
		}
		return getSession().createSQLQuery(sql.toString()).addEntity(SysPermission.class).list();
	}
	
}
