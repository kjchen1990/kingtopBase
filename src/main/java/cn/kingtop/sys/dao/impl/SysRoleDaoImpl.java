package cn.kingtop.sys.dao.impl;

import java.util.List;

import cn.kingtop.sys.dao.ISysRoleDao;
import cn.kingtop.sys.model.SysRole;

import org.kingtop.dao.BaseDao;
import org.springframework.stereotype.Repository;


@Repository
public class SysRoleDaoImpl extends BaseDao<SysRole> implements ISysRoleDao {
	
	public SysRoleDaoImpl()	{
		
	}

	@Override
	public List<SysRole> findSysRoleByIds(long userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_role r where r.id in ");
		sql.append(" (select t.id from SYS_USER_ROLE t,sys_user u where u.id=" + userId);
		sql.append(" and t.user_id=u.id)");
		return getSession().createSQLQuery(sql.toString()).addEntity(SysRole.class).list();
	}
	
}
