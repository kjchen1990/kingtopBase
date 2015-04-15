package org.kingtop.dao;

import java.io.Serializable;
import java.util.List;

import org.kingtop.lang.BaseException;
import org.kingtop.sys.Page;

public interface IBaseDao<T> {

	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public T getObject(long id) throws BaseException ;

	public T getObject(int id) throws BaseException ;

	public T getObject(String id) throws BaseException ;

	public T getObject(Serializable id) throws BaseException ;

	/**
	 * 添加
	 * @param modelObject 对象
	 * @throws BaseException
	 */
	public void addObject(T modelObject) throws BaseException ;

	/**
	 * 添加或更新，存在则更新，不存在则添加
	 * @param modelObject
	 * @throws BaseException
	 */
	public void saveOrUpdate(T modelObject) throws BaseException;

	/**
	 * 更新
	 * @param modelObject
	 * @throws BaseException
	 */
	public void updateObject(T modelObject) throws BaseException ;

	/**
	 *通过id删除对象
	 * @param id
	 * @throws BaseException
	 */
	public void deleteObject(long id) throws BaseException ;

	/**
	 *通过id删除对象
	 * @param id
	 * @throws BaseException
	 */
	public void deleteObject(String id) throws BaseException ;
	/**
	 *通过id删除对象
	 * @param id
	 * @throws BaseException
	 */
	public void deleteObject(int id);

	/**
	 * 删除对象
	 * @param t 对象
	 */
	public void deleteObject(T t) ;

	/**
	 * 获取所有对象的数据
	 * @return
	 * @throws BaseException
	 */
	public List<T> getObjects() throws BaseException ;

	/**
	 * 保存多个对象
	 * @param modelList 多个对象
	 * @throws BaseException
	 */
	public void saveList(final List<T> modelList) throws BaseException ;

	/**
	 * 通过hql语句和相关参数来获取对象数据
	 * @param hql hql语句，参数用?
	 * @param paramlist 参数
	 * @return
	 */
	public List getObjectsByParams(final String hql, final Object... paramlist);

	/**
	 * 分页查询当前对象
	 * @param currPage 页码
	 * @param pageSize 每页条数
	 * @return
	 * @throws BaseException
	 */
	public Page findPage(int currPage, int pageSize) throws BaseException ;
	
	/**
	 * 通过hql语句分页查询
	 * @param queryString hql语句
	 * @param currPage 页码
	 * @param pageSize 每页条数
	 * @return
	 * @throws BaseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page findPage(String queryString, int currPage,
			int pageSize) throws BaseException;

	/**
	 * 执行hql更新语句，并设置相关参数
	 * @param hql 更新语句
	 * @param paramlist 参数
	 * @return
	 */
	public int executeHql(final String hql, final Object... paramlist);
	
	/**
	 * 通过sql查询数据
	 * @param sql
	 * @return
	 * @throws BaseException
	 */
	public List findBySQL(final String sql) throws BaseException;
	
	/**
	 * 通过sql查询数据，设置参数
	 * @param sql sql语句，有参数则用?
	 * @param paramlist 参数
	 * @return
	 * @throws BaseException
	 */
	public List findBySQL(final String sql, final Object... paramlist)
			throws BaseException;
	
	/**
	 * 通过sql语句，设置参数和返回类型
	 * @param sql sql语句
	 * @param outModelClass 返回的值类型
	 * @param paramlist 参数列表
	 * @return
	 */
	public List findByFreeSQL(final String sql, final Class outModelClass,
			 final Object... paramlist);
	
	/**
	 * 通过sql语句，设置返回类型
	 * @param sql sql语句
	 * @param outModelClass 返回的值类型
	 * @return
	 * @throws BaseException
	 */
	public List findByFreeSQL(final String sql, final Class outModelClass) throws BaseException;
	
	/**
	 * 分页查询当前对象的数据
	 * @param sql 查询语句
	 * @param currPage 页码
	 * @param pageSize 每页条数
	 * @return
	 * @throws BaseException
	 */
	public Page findPageBySQL(String sql, int currPage, int pageSize) throws BaseException;
	
	/**
	 * 通过sql分页查询
	 * @param sql
	 * @param currPage 页码
	 * @param pageSize 每页条数
	 * @return
	 * @throws BaseException
	 */
	public Page findPageByFreeSQL(String sql, int currPage, int pageSize) throws BaseException;
	
	/**
	 * 通过sql分页查询 
	 * @param sql
	 * @param currPage 页码
	 * @param pageSize 每页条数
	 * @param outModelClass 要返回的数据类型
	 * @return
	 */
	public Page findPageByFreeSQL(String sql, int currPage, int pageSize, Class outModelClass);
	
	/**
	 * 通过sql语句更新
	 * @param sql 更新语句
	 * @param paramlist 参数
	 * @return 更新条数
	 */
	public int executeSql(final String sql, final Object... paramlist);
}
