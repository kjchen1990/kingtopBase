package cn.kingtop.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.kingtop.dao.BaseDao;
import org.springframework.stereotype.Repository;

import cn.kingtop.sys.dao.ISysUserDao;
import cn.kingtop.sys.model.SysUser;

@Repository
public class SysUserDaoImpl extends BaseDao<SysUser> implements ISysUserDao {

	public SysUserDaoImpl() {

	}

	@Override
	public SysUser findByUsername(String username) {
		// Query q = this.getSession().createQuery("from SysUser u where u.userName = :userName");
		// q.setString("userName", username);
		Query q = getSession().createSQLQuery("select * from SYS_USER t where t.user_name='" + username + "'").addEntity(SysUser.class);
		List<SysUser> list = q.list();
		if (list.size() > 0) return list.get(0);
		return null;
	}

}
