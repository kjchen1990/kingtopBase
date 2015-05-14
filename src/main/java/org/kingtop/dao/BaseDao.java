package org.kingtop.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;
import org.kingtop.util.BeanPropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<T> implements IBaseDao<T> {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected Class modelClass;
	protected String defaultOrder = "";

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.modelClass = getSuperClassType(getClass());
	}

	/**
	 * 通过反射，获得定义Class声明时的父类的范型参数类型.
	 * 
	 * @param clazz
	 *            the clazz
	 * 
	 * @return the super class type
	 */
	@SuppressWarnings("rawtypes")
	private Class getSuperClassType(Class clazz) {

		Type[] params = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();

		if (!(params[0] instanceof Class)) {

			return Object.class;
		}
		return (Class) params[0];
	}

	public final T getObject(long id) throws BaseException {
		try {
			Serializable idTemp = new Long(id);
			return getObject(idTemp);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, id);
		}
	}

	public final T getObject(int id) throws BaseException {
		try {
			Serializable idTemp = new Long(id);
			return getObject(idTemp);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, id);
		}
	}

	public final T getObject(String id) throws BaseException {
		try {
			Serializable idTemp = new String(id);
			return getObject(idTemp);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, id);
		}
	}

	@SuppressWarnings("unchecked")
	public final T getObject(Serializable id) throws BaseException {
		try {
			return (T) getSession().get(modelClass, id);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, id);
		}
	}

	public final void addObject(T modelObject) throws BaseException {
		try {
			getSession().save(modelObject);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, modelObject);
		}
	}

	public final void saveOrUpdate(T modelObject) throws BaseException {
		try {
			getSession().saveOrUpdate(modelObject);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, modelObject);
		}
	}

	public final void updateObject(T modelObject) throws BaseException {
		try {
			getSession().update(modelObject);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, modelObject);
		}
	}

	public final void deleteObject(long id) throws BaseException {
		deleteObject(id);
	}

	public final void deleteObject(String id) throws BaseException {
		deleteObject(Integer.valueOf(id));
	}

	public void deleteObject(int id) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "delete from " + modelClass.getName() + " where id = :id";
			Query q = session.createQuery(sql);
			q.setInteger("id", id);
			q.executeUpdate();
		}
		catch (HibernateException e) {
			throw new BaseException(e, this.log, id);
		}
	}

	public void deleteObject(T t) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(t);
		}
		catch (HibernateException e) {
			throw new BaseException(e, this.log, t);
		}
	}

	public final List<T> getObjects() throws BaseException {
		String queryString = "";
		try {
			queryString = "from " + this.modelClass.getSimpleName() + getDefaultOrder();
			return findByHql(queryString);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, queryString);
		}
	}

	public final void saveList(final List<T> modelList) throws BaseException {
		try {
			T t = null;
			for (int i = 0; i < modelList.size(); i++) {
				t = (T) modelList.get(i);
				getSession().save(t);
				// 批插入的对象立即写入数据库并释放内存
				if (i % 10 == 0) {
					getSession().flush();
					getSession().clear();
				}
			}
		}
		catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	/**
	 * 通过Hql语句来查询数据
	 * @param queryString sql语句
	 * @return
	 * @throws BaseException
	 */
	@SuppressWarnings("unchecked")
	public final List<T> findByHql(String hql) throws BaseException {
		try {
			Query q = getSession().createQuery(hql);
			return q.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, this.log, hql);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHql(final String hql, final Object... paramlist) throws BaseException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			setParameters(query, paramlist);
			return query.list();
		}
		catch (HibernateException e) {
			throw new BaseException(e, this.log, hql);
		}
	}

	/**
	 * 设置参数
	 * @param query 查询
	 * @param paramlist 参数
	 */
	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					query.setTimestamp(i, (Date) paramlist[i]);
				}
				else if (paramlist[i] instanceof String) {
					query.setString(i, (String) paramlist[i]);
				}
				else if (paramlist[i] instanceof Integer) {
					query.setInteger(i, Integer.valueOf(paramlist[i].toString()));
				}
				else if (paramlist[i] instanceof Long) {
					query.setLong(i, Long.valueOf(paramlist[i].toString()));
				}
				else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final Page<T> findPage(int currPage, int pageSize) throws BaseException {
		String queryString = "";
		try {
			queryString = "from " + this.modelClass.getSimpleName() + getDefaultOrder();
			System.out.println(queryString);
			int totalCount = countDataTotal(queryString, getSession());

			int startRow = (currPage - 1) * pageSize;
			if (startRow < 0) startRow = 0;
			Query query = getSession().createQuery(queryString).setFirstResult(startRow).setMaxResults(pageSize);

			List modelList = query.list();
			return new Page(modelList, totalCount, currPage, pageSize);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, this.log, queryString);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page findPageByHql(String hql, int currPage, int pageSize) throws BaseException {
		try {
			Session session = getSession();
			int totalCount = countDataTotal(hql, session);

			int startRow = (currPage - 1) * pageSize;
			if (startRow < 0) startRow = 0;
			Query query = session.createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize);

			List modelList = query.list();
			return new Page(modelList, totalCount, currPage, pageSize);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, hql);
		}
	}

	/**
	 * 获取总条数
	 * @param queryString hql语句
	 * @param session
	 * @return
	 * @throws BaseException
	 */
	protected int countDataTotal(String queryString, Session session) throws BaseException {
		try {
			int totalCount = 0;
			String lowerHql = queryString.trim().toLowerCase();
			int fromPos = 0;
			int orderPos = lowerHql.length();
			if (!lowerHql.startsWith("from ")) fromPos = lowerHql.indexOf(" from ") + 1;
			orderPos = lowerHql.indexOf(" order by ") + 1;
			String countHql;
			if (orderPos != 0)
				countHql = (new StringBuilder("select count(*) ")).append(queryString.substring(fromPos, orderPos)).toString();
			else
				countHql = (new StringBuilder("select count(*) ")).append(queryString.substring(fromPos)).toString();
			int distinctPos = lowerHql.indexOf(" distinct ");
			if (distinctPos >= 0 && distinctPos < fromPos) {
				totalCount = session.createQuery(queryString).list().size();
			}
			else {
				Object value = session.createQuery(countHql).uniqueResult();
				if (value == null)
					totalCount = 0;
				else
					totalCount = ((Long) value).intValue();
			}
			return totalCount;
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, queryString);
		}
	}

	public List<T> findBySQL(final String sql) throws BaseException {
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(this.modelClass);
			return sqlQuery.list();
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	public List<T> findBySQL(final String sql, final Object... paramlist) throws BaseException {
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			setSqlParameters(sqlQuery, paramlist);
			sqlQuery.addEntity(this.modelClass);
			return sqlQuery.list();
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	protected void setSqlParameters(SQLQuery sqlQuery, Object[] paramlist) {
		if (paramlist != null && paramlist.length > 0) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					// TODO 难道这是bug 使用setParameter不行？？
					sqlQuery.setTimestamp(i, (Date) paramlist[i]);
				}
				else if (paramlist[i] instanceof String) {
					sqlQuery.setString(i, (String) paramlist[i]);
				}
				else if (paramlist[i] instanceof Integer) {
					sqlQuery.setInteger(i, Integer.valueOf(paramlist[i].toString()));
				}
				else if (paramlist[i] instanceof Long) {
					sqlQuery.setLong(i, Long.valueOf(paramlist[i].toString()));
				}
				else {
					sqlQuery.setParameter(i, paramlist[i]);
				}
			}
		}
	}

	public List findByFreeSQL(final String sql, final Class outModelClass, final Object... paramlist) throws BaseException {
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			setSqlParameters(sqlQuery, paramlist);
			sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List modelList = sqlQuery.list();
			List resultList = BeanPropertyUtil.converMapToBean(outModelClass, modelList);
			return resultList;
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	public List findByFreeSQL(final String sql, final Class outModelClass) throws BaseException {
		try {
			return findByFreeSQL(sql, outModelClass, new Object[] {});
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	@SuppressWarnings("unchecked")
	public final Page<T> findPageBySQL(String sql, int currPage, int pageSize) throws BaseException {
		return findPageByFreeSQL(sql, currPage, pageSize, this.modelClass);
	}

	public final Page findPageByFreeSQL(String sql, int currPage, int pageSize) throws BaseException {
		try {
			Session session = getSession();
			int totalCount = countDataTotalBySQL(sql, session);

			int startRow = (currPage - 1) * pageSize;
			if (startRow < 0) startRow = 0;
			Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(startRow).setMaxResults(pageSize);

			List modelList = query.list();
			return new Page(modelList, totalCount, currPage, pageSize);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final Page findPageByFreeSQL(String sql, int currPage, int pageSize, Class outModelClass) throws BaseException {
		try {
			Session session = getSession();
			int totalCount = countDataTotalBySQL(sql, session);

			int startRow = (currPage - 1) * pageSize;
			if (startRow < 0) startRow = 0;
			Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult(startRow).setMaxResults(pageSize);
			List modelList = query.list();
			List resultList = BeanPropertyUtil.converMapToBean(outModelClass, modelList);

			return new Page(resultList, totalCount, currPage, pageSize);
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	public final int executeSql(final String sql, final Object... paramlist) throws BaseException {
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			setSqlParameters(query, paramlist);
			Object result = query.executeUpdate();
			return result == null ? 0 : ((Integer) result).intValue();
		}
		catch (HibernateException e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	public int executeHql(final String hql, final Object... paramlist) throws BaseException {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			setParameters(query, paramlist);
			Object result = query.executeUpdate();
			return result == null ? 0 : ((Integer) result).intValue();
		}
		catch (HibernateException e) {
			throw new BaseException(e, this.log, hql);
		}
	}

	/**
	 * 获取总条数
	 * @param sql
	 * @param session
	 * @return
	 * @throws BaseException
	 */
	protected int countDataTotalBySQL(String sql, Session session) throws BaseException {
		try {
			int totalCount = 0;
			String lowerHql = sql.trim().toLowerCase();
			int fromPos = 0;
			int orderPos = lowerHql.length();
			fromPos = lowerHql.indexOf(" from ") + 1;
			orderPos = lowerHql.indexOf(" order by ") + 1;
			String countSql;
			if (orderPos != 0)
				countSql = (new StringBuilder("select count(*) as num ")).append(sql.substring(fromPos, orderPos)).toString();
			else
				countSql = (new StringBuilder("select count(*) as num ")).append(sql.substring(fromPos)).toString();
			int distinctPos = lowerHql.indexOf(" distinct ");
			if (distinctPos >= 0 && distinctPos < fromPos) {
				totalCount = session.createQuery(sql).list().size();
			}
			else {
				Long value = (Long) session.createSQLQuery(countSql).uniqueResult();
				if (value == null)
					totalCount = 0;
				else
					totalCount = value.intValue();
			}
			return totalCount;
		}
		catch (Exception e) {
			throw new BaseException(e, this.log, sql);
		}
	}

	/**
	 * 获取排序方式
	 * @return
	 */
	private String getDefaultOrder() {
		if (this.defaultOrder.trim().equals("")) {
			return "";
		}
		String lowerHql = this.defaultOrder.trim().toLowerCase();
		if (lowerHql.indexOf("order by") == 0) {
			return " " + this.defaultOrder;
		}
		return " order by " + this.defaultOrder;
	}

}
