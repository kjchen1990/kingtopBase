package org.kingtop.module;

import java.util.List;

public abstract class PdTreeModel implements IPdTreeModel {
	protected int level = 0;
	protected String levelString = "";
	protected String levelHtml = "";
	protected boolean isLeaf;
	protected List<Object> children;

	public abstract long getNodeId();

	public abstract long getParentId();

	public abstract String getNodeName();

	public abstract String getNodeValue();

	public long getLevel() {
		return this.level;
	}

	public String getLevelString() {
		return this.levelString;
	}

	public String getLevelHtml() {
		return this.levelHtml;
	}

	public void setLevel(int level) {
		this.level = level;
		String strLine = "├ ";
		for (int i = 0; i < level; i++) {
			strLine = "│" + strLine;
			this.levelHtml += "&nbsp;&nbsp;";
		}
		this.levelString = strLine;
	}

	public boolean isLeaf() {
		return this.isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}
}
