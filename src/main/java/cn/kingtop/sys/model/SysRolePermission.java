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
@Table(name="SYS_ROLE_PERMISSION")
public class SysRolePermission implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_ROLE_PERMISSION_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	T_SYS_ROLE表的id
	*/
	@Column(name="ROLE_ID")
	private Long roleId;
	
	/**
	*	T_SYS_PERMISSION表的id
	*/
	@Column(name="PERMISSION_ID")
	private Long permissionId;
	

	public SysRolePermission(){
		
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}
	
	public Long getRoleId()
	{
		return this.roleId;
	}

	public void setPermissionId(Long permissionId)
	{
		this.permissionId = permissionId;
	}
	
	public Long getPermissionId()
	{
		return this.permissionId;
	}

}