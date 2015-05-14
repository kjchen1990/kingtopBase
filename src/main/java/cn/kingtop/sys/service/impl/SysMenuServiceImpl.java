package cn.kingtop.sys.service.impl;

import java.util.List;

import org.kingtop.lang.BaseException;
import org.kingtop.service.BaseService;
import org.kingtop.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kingtop.sys.bean.SysMenuBean;
import cn.kingtop.sys.dao.ISysMenuDao;
import cn.kingtop.sys.model.SysMenu;
import cn.kingtop.sys.service.ISysMenuService;


@Service("SysMenuService")
public class SysMenuServiceImpl extends BaseService<SysMenu> implements ISysMenuService {
	private ISysMenuDao sysMenuDao;

	public ISysMenuDao getSysMenuDao() {
		return sysMenuDao;
	}

	@Autowired
	public void setSysMenuDao(ISysMenuDao sysMenuDao) {
		this.sysMenuDao = sysMenuDao;
	}

	public SysMenuServiceImpl()	{
		
	}

	@Override
	public List<Object> getUserMenus(long userId, String sysFlag) {
		try {
			List<SysMenuBean> list = this.sysMenuDao.getAllMenus(sysFlag.trim());
			List<Object> resultList = TreeUtil.getPdTreeList(list, 0);
			return resultList;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, log);
		}
	}

	

}