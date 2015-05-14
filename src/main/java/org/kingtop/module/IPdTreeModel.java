package org.kingtop.module;

import java.util.List;

/**
 * 树状图节点
 * @author Administrator
 * 
 */
public abstract interface IPdTreeModel {
	public abstract long getNodeId();

	public abstract long getParentId();

	public abstract String getNodeName();

	public abstract String getNodeValue();

	public abstract void setLevel(int paramInt);

	public abstract void setLeaf(boolean paramBoolean);

	public abstract List<Object> getChildren();

	public abstract void setChildren(List<Object> children);
}
