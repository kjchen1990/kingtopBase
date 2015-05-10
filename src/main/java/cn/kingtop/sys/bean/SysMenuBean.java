package cn.kingtop.sys.bean;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Administrator
 * @version 1.0
 * @created 2015-01-29 14:56:25
 */
public class SysMenuBean implements java.io.Serializable {

	private Long id;
	
	/**
	*	菜单名称
	*/
	private String name;
	
	/**
	*	菜单地址
	*/
	private String url;
	
	/**
	*	父id
	*/
	private Long parentId;
	
	/**
	*	是否可用，1：可以；0：不可以
	*/
	private int available;
	
	/**
	*	系统中验证时使用的权限标识
	*/
	private String permission;
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getUrl()
	{
		return this.url;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	
	public Long getParentId()
	{
		return this.parentId;
	}

	public void setAvailable(int available)
	{
		this.available = available;
	}
	
	public int getAvailable()
	{
		return this.available;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}