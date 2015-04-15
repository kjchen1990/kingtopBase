package org.kingtop.service;

import java.util.List;

import org.kingtop.dao.BaseDao;
import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;
import org.kingtop.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T> implements IBaseService<T> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private BaseDao<T> baseDao;

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}
	
	@Autowired
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public Object getObject(String id) throws BaseException {
		try {
			AssertUtil.notEmpty(id, "9001", this.log);
			return this.baseDao.getObject(id);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public Object getObject(long id) throws BaseException {
		try {
			return this.baseDao.getObject(id);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public Object getObject(int id) throws BaseException {
		try {
			return this.baseDao.getObject(id);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void addObject(T modelObject) throws BaseException {
		try {
			AssertUtil.notNull(modelObject, "9002", this.log);
			this.baseDao.addObject(modelObject);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void saveObject(T modelObject) throws BaseException {
		try {
			AssertUtil.notNull(modelObject, "9002", this.log);
			this.baseDao.addObject(modelObject);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void updateObject(T modelObject) throws BaseException {
		try {
			AssertUtil.notNull(modelObject, "9002", this.log);
			this.baseDao.updateObject(modelObject);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void deleteObject(String id) throws BaseException {
		try {
			AssertUtil.notEmpty(id, "9001", this.log);
			this.baseDao.deleteObject(id);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void deleteObject(String[] ids) throws BaseException {
		try {
			for (int i = 0; i < ids.length; i++) {
				AssertUtil.notEmpty(ids[i], "9001", this.log);
				this.baseDao.deleteObject(ids[i]);
			}
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public void deleteObject(long[] ids) throws BaseException {
		try {
			for (int i = 0; i < ids.length; i++)
				this.baseDao.deleteObject(ids[i]);
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	public List getObjects() throws BaseException {
		try {
			return this.baseDao.getObjects();
		} catch (Exception e) {
			throw new BaseException(e, this.log);
		}
	}

	//@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public Page<T> findPage(int currPage, int pageSize) throws BaseException {
		try {
			return this.baseDao.findPage(currPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, this.log);
		}
	}
}
