package cn.kingtop.sys.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author Administrator
 * @version 1.0
 * @created 2015-01-29 14:56:26
 */
@Entity
@Table(name="SYS_USER")
public class SysUser implements java.io.Serializable {

	@Id 
	@Column(name="ID")
	@SequenceGenerator(name="seq", sequenceName="SYS_USER_ID_SEQU")
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seq")
	private Long id;
	
	/**
	*	用户登录名
	*/
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	*	名称
	*/
	@Column(name="NAME")
	private String name;
	
	/**
	*	密码
	*/
	@Column(name="PASSWORD")
	private String password;
	
	/**
	*	密码设置时间,用于密码过期提醒
	*/
	@Column(name="PASSWORD_SET_TIME")
	private Date passwordSetTime;
	
	/**
	*	部门id，根据用户所在的部门，确定其所在的地区
	*/
	@Column(name="DEPT_ID")
	private Long deptId;
	
	/**
	*	是否锁定标识，0：未锁定；1：锁定
	*/
	@Column(name="LOCK_FLAG")
	private int lockFlag;
	
	/**
	*	性别
	*/
	@Column(name="SEX")
	private String sex;
	
	/**
	*	职务id
	*/
	@Column(name="POSITION_ID")
	private Long positionId;
	
	/**
	*	联系电话
	*/
	@Column(name="PHONE")
	private String phone;
	
	/**
	*	备注
	*/
	@Column(name="REMARK")
	private String remark;
	
	/**
	*	加密密码的盐
	*/
	@Column(name="SALT")
	private String salt;
	
	/**
	 * 
	 */
	@Transient
	private String credentialsSalt;
	

	public SysUser(){
		
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getUserName()
	{
		return this.userName;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return this.password;
	}

	public void setPasswordSetTime(Date passwordSetTime)
	{
		this.passwordSetTime = passwordSetTime;
	}
	
	public Date getPasswordSetTime()
	{
		return this.passwordSetTime;
	}

	public void setDeptId(Long deptId)
	{
		this.deptId = deptId;
	}
	
	public Long getDeptId()
	{
		return this.deptId;
	}

	public void setLockFlag(int lockFlag)
	{
		this.lockFlag = lockFlag;
	}
	
	public int getLockFlag()
	{
		return this.lockFlag;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public String getSex()
	{
		return this.sex;
	}

	public void setPositionId(Long positionId)
	{
		this.positionId = positionId;
	}
	
	public Long getPositionId()
	{
		return this.positionId;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getPhone()
	{
		return this.phone;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	public String getRemark()
	{
		return this.remark;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	
	public String getSalt()
	{
		return this.salt;
	}
	
	public String getCredentialsSalt() {
		return userName + salt;
	}

}