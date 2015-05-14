package cn.kingtop.sys.bean;

import javax.persistence.Column;

import org.kingtop.module.IPdTreeModel;
import org.kingtop.module.PdTreeModel;

/**
 * @author Administrator
 * @version 1.0
 * @created 2015-01-29 14:56:25
 */
public class SysMenuBean extends PdTreeModel implements java.io.Serializable, IPdTreeModel {

	private Long id;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单地址
	 */
	private String url;

	/**
	 * 父id
	 */
	private long parentId;

	/**
	 * 是否可用，1：可以；0：不可以
	 */
	private int available;

	/**
	 * 系统中验证时使用的权限标识
	 */
	private String permission;
	
	/**
	 * 系统标识
	 */
	private String sysFlag;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public long getParentId() {
		return this.parentId;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getAvailable() {
		return this.available;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public long getNodeId() {
		return this.id;
	}

	@Override
	public String getNodeName() {
		return this.name;
	}

	@Override
	public String getNodeValue() {
		return this.url;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

}