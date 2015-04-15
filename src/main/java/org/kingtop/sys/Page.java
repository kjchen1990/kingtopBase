package org.kingtop.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7893956550587618043L;
	public static final int PAGESIZE = 10;
	private int pageSize = 10;
	private int pageNumber;
	private int totalCount = 0;
	private List<T> items = new ArrayList<T>();

	public Page(List<T> items, int totalCount, int pageNumber, int pageSize) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.items = items;
		this.pageNumber = pageNumber;
		if (this.pageNumber > getLastPageNumber())
			this.pageNumber = getLastPageNumber();
	}

	public List<T> getItems() {
		return this.items;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public int getNextPageNumber() {
		return getPageNumber() + 1;
	}

	public int getPreviousPageNumber() {
		return getPageNumber() - 1;
	}

	public boolean isFirstPage() {
		return getPageNumber() == 1;
	}

	public boolean isLastPage() {
		return getPageNumber() + 1 >= getLastPageNumber();
	}

	public boolean hasNextPage() {
		return getLastPageNumber() > getPageNumber() + 1;
	}

	public boolean hasPreviousPage() {
		return getPageNumber() > 1;
	}

	public int getFirstPageNumber() {
		return 1;
	}

	public int getLastPageNumber() {
		if (this.pageSize == 0) {
			return 0;
		}
		return this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : this.totalCount / this.pageSize + 1;
	}

	public Iterator<T> iterator() {
		return getItems().iterator();
	}

	public String getInfo() {
		String info = "";
		info = "共有" + getTotalCount() + "条记录，当前(" + getPageNumber() + "/"
				+ getLastPageNumber() + "页)";
		return info;
	}

}
