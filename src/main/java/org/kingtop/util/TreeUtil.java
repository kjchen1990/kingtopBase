package org.kingtop.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kingtop.lang.BaseException;
import org.kingtop.module.IPdTreeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeUtil {

	public static Logger log = LoggerFactory.getLogger(TreeUtil.class);
	

	/**
	 * 构建树状图，默认最多20个级别
	 * @param dataList 数据
	 * @param rootId 根节点id
	 * @return
	 */
	public static<T extends IPdTreeModel> Map getPdTree(List<T> dataList, long rootId) {
		return getPdTree(dataList, rootId, "", 20);
	}

	/**
	 * 构建树状图
	 * @param dataList 数据
	 * @param rootId 根节点id
	 * @param levelNumber 最多级别数
	 * @return
	 */
	public static<T extends IPdTreeModel> Map getPdTree(List<T> dataList, long rootId, int levelNumber) {
		return getPdTree(dataList, rootId, "", levelNumber);
	}

	/**
	 * 构建树状图，默认最多20个级别
	 * @param dataList 数据
	 * @param rootId 根节点id
	 * @param type 类型
	 * @return
	 */
	public static<T extends IPdTreeModel> Map getPdTree(List<T> dataList, long rootId, String type) {
		return getPdTree(dataList, rootId, type, 20);
	}

	/**
	 * 构建树状图，默认最多20个级别
	 * @param dataList 数据
	 * @param rootId 根节点id
	 * @param type 类型
	 * @param maxLevelNumber 最多级别数
	 * @return
	 */
	public static<T extends IPdTreeModel> Map getPdTree(List<T> dataList, long rootId, String type, int maxLevelNumber) {
		Map rootMap = new HashMap();
		try {
			for (Iterator localIterator = dataList.iterator(); localIterator.hasNext();) {
				Object dataObject = localIterator.next();
				IPdTreeModel treeModelObject = (IPdTreeModel) dataObject;

				if (treeModelObject.getNodeId() == rootId) {
					List childList = getChildNodesList(dataList, treeModelObject.getNodeId(), type, maxLevelNumber - 1);
					rootMap.put("main", dataObject);
					if (childList.size() > 0) {
						rootMap.put("children", childList);
					}
					rootMap.put("type", type);
					break;
				}
			}
		}
		catch (Exception e) {
			throw new BaseException(e, log);
		}
		return rootMap;
	}

	/**
	 * 构建树状图中获取子节点
	 * @param dataList 数据
	 * @param rootId 根节点id
	 * @param type 类型
	 * @param maxLevelNumber 最多级别数
	 * @return
	 */
	public static<T extends IPdTreeModel> List<Map> getChildNodesList(List<T> dataList, long parentId, String type, int maxLevelNumber) {
		List childNodes = new ArrayList();
		if (maxLevelNumber > 0) {
			try {
				for (Iterator localIterator = dataList.iterator(); localIterator.hasNext();) {
					Object dataObject = localIterator.next();
					Map currNodeMap = new HashMap();
					IPdTreeModel treeModelObject = (IPdTreeModel) dataObject;
					if (parentId == treeModelObject.getParentId()) {
						currNodeMap.put("main", dataObject);
						List childList = getChildNodesList(dataList, treeModelObject.getNodeId(), type, maxLevelNumber - 1);
						if (childList.size() > 0) {
							currNodeMap.put("children", childList);
						}
						currNodeMap.put("type", type);
						childNodes.add(currNodeMap);
					}
				}
			}
			catch (Exception e) {
				throw new BaseException(e, log);
			}
		}
		return childNodes;
	}
	
	/**
	 * 将数据构建成树
	 * @param dataList 数据
	 * @param parentId 父id
	 * @return
	 */
	public static <T extends IPdTreeModel> List<Object> getPdTreeList(List<T> dataList, long parentId){
		try {
			List<Object> resultList = new ArrayList<Object>();
			for (Object object : dataList) {
				IPdTreeModel treeModel = (IPdTreeModel) object;
				if(parentId == treeModel.getParentId()){
					List<Object> childrenList = getPdTreeList(dataList, treeModel.getNodeId());
					treeModel.setChildren(childrenList);
					resultList.add(object);
				}
			}
			return resultList;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e, log);
		}
	}
	
}
