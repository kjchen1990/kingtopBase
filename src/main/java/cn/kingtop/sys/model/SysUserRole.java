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
@Table(name="SYS_USER_ROLE")
public class SysUserRole implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_USER_ROLE_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	用户id
	*/
	@Column(name="USER_ID")
	private Long userId;
	
	/**
	*	角色id
	*/
	@Column(name="ROLE_ID")
	private Long roleId;
	

	public SysUserRole(){
		
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public Long getUserId()
	{
		return this.userId;
	}

	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}
	
	public Long getRoleId()
	{
		return this.roleId;
	}

}