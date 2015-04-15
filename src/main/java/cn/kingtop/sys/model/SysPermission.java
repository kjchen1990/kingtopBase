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
 * @created 2015-01-29 14:56:26
 */
@Entity
@Table(name="SYS_PERMISSION")
public class SysPermission implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_PERMISSION_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	前端显示名称
	*/
	@Column(name="NAME")
	private String name;
	
	/**
	*	系统中验证时使用的权限标识
	*/
	@Column(name="PERMISSION")
	private String permission;
	
	/**
	*	详细描述
	*/
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	*	是否可用，1：可以；0：不可以
	*/
	@Column(name="AVAILABLE")
	private int available;
	

	public SysPermission(){
		
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

	public void setPermission(String permission)
	{
		this.permission = permission;
	}
	
	public String getPermission()
	{
		return this.permission;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}

	public void setAvailable(int available)
	{
		this.available = available;
	}
	
	public int getAvailable()
	{
		return this.available;
	}

}