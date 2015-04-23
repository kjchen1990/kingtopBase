package org.kingtop.service;

import java.util.List;

import org.kingtop.dao.IBaseDao;
import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> implements IBaseService<T> {
	protected Logger log = LoggerFactory.getLogger(getClass());

	private IBaseDao<T> baseDao;

	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public T getObject(String id) throws BaseException {
		return this.baseDao.getObject(id);
	}

	public T getObject(long id) throws BaseException {
		return this.baseDao.getObject(id);
	}

	public T getObject(int id) throws BaseException {
		return this.baseDao.getObject(id);
	}

	public void addObject(T modelObject) throws BaseException {
		this.baseDao.addObject(modelObject);
	}

	public void saveObject(T modelObject) throws BaseException {
		this.baseDao.addObject(modelObject);
	}

	public void updateObject(T modelObject) throws BaseException {
		this.baseDao.updateObject(modelObject);
	}

	public void deleteObject(String id) throws BaseException {
		this.baseDao.deleteObject(id);
	}

	public void deleteObject(String[] ids) throws BaseException {
		for (int i = 0; i < ids.length; i++) {
			this.baseDao.deleteObject(ids[i]);
		}
	}

	public void deleteObject(long[] ids) throws BaseException {
		for (int i = 0; i < ids.length; i++)
			this.baseDao.deleteObject(ids[i]);
	}

	public List getObjects() throws BaseException {
		return this.baseDao.getObjects();
	}

	public Page<T> findPage(int currPage, int pageSize) throws BaseException {
		return this.baseDao.findPage(currPage, pageSize);
	}

	@Override
	public void saveOrUpdate(T modelObject) throws BaseException {
		this.baseDao.saveOrUpdate(modelObject);
	}

	@Override
	public void deleteObject(long id) throws BaseException {
		this.baseDao.deleteObject(id);
	}

	@Override
	public void deleteObject(int id) throws BaseException {
		this.baseDao.deleteObject(id);
	}

	@Override
	public void deleteObject(T t) throws BaseException {
		this.baseDao.deleteObject(t);
	}

	@Override
	public void saveList(List<T> modelList) throws BaseException {
		this.baseDao.saveList(modelList);
	}

	@Override
	public List<T> findByHql(String hql) throws BaseException {
		return this.baseDao.findByHql(hql);
	}

	@Override
	public List findByHql(String hql, Object... paramlist) throws BaseException {
		return this.baseDao.findByHql(hql, paramlist);
	}

	@Override
	public Page findPageHql(String hql, int currPage, int pageSize) throws BaseException {
		return this.findPageHql(hql, currPage, pageSize);
	}

	@Override
	public int executeHql(String hql, Object... paramlist) throws BaseException {
		return this.executeHql(hql, paramlist);
	}

	@Override
	public List<T> findBySQL(String sql) throws BaseException {
		return this.findBySQL(sql);
	}

	@Override
	public List<T> findBySQL(String sql, Object... paramlist) throws BaseException {
		return this.findBySQL(sql, paramlist);
	}

	@Override
	public List findByFreeSQL(String sql, Class outModelClass, Object... paramlist) throws BaseException {
		return this.findByFreeSQL(sql, outModelClass, paramlist);
	}

	@Override
	public List findByFreeSQL(String sql, Class outModelClass) throws BaseException {
		return this.findByFreeSQL(sql, outModelClass);
	}

	@Override
	public Page<T> findPageBySQL(String sql, int currPage, int pageSize) throws BaseException {
		return this.findPageBySQL(sql, currPage, pageSize);
	}

	@Override
	public Page findPageByFreeSQL(String sql, int currPage, int pageSize) throws BaseException {
		return this.findPageByFreeSQL(sql, currPage, pageSize);
	}

	@Override
	public Page findPageByFreeSQL(String sql, int currPage, int pageSize, Class outModelClass) throws BaseException {
		return this.findPageByFreeSQL(sql, currPage, pageSize, outModelClass);
	}

	@Override
	public int executeSql(String sql, Object... paramlist) throws BaseException {
		return this.baseDao.executeSql(sql, paramlist);
	}
}
