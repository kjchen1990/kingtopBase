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
@Table(name="SYS_MENU_PERMISSION")
public class SysMenuPermission implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_MENU_PERMISSION_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	菜单id
	*/
	@Column(name="MENU_ID")
	private Long menuId;
	
	/**
	*	权限id
	*/
	@Column(name="PERMISSION_ID")
	private Long permissionId;
	

	public SysMenuPermission(){
		
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setMenuId(Long menuId)
	{
		this.menuId = menuId;
	}
	
	public Long getMenuId()
	{
		return this.menuId;
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