package cn.kingtop.sys.model;
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
@Entity
@Table(name="SYS_MENU")
public class SysMenu implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_MENU_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	菜单名称
	*/
	@Column(name="NAME")
	private String name;
	
	/**
	*	菜单地址
	*/
	@Column(name="URL")
	private String url;
	
	/**
	*	父id
	*/
	@Column(name="PARENT_ID")
	private Long parentId;
	
	/**
	*	是否可用，1：可以；0：不可以
	*/
	@Column(name="AVAILABLE")
	private int available;
	
	/**
	 * 系统标识
	 */
	@Column(name="SYS_FLAG")
	private String sysFlag;
	

	public SysMenu(){
		
	}
	
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

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

}