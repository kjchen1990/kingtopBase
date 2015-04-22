package cn.kingtop.shiro.realms;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.kingtop.sys.model.SysUser;
import cn.kingtop.sys.service.ISysUserService;

/**
 * <p>
 * User: chen jinbo
 * <p>
 * Date: 15-01-16
 * <p>
 * Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {
	
	private Cache<String, SimpleAuthorizationInfo> authorizationCache;
	private Cache<String, SysUser> userCache;

	private ISysUserService sysUserService;

	public ISysUserService getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
	public UserRealm(CacheManager cacheManager){
		this.authorizationCache = cacheManager.getCache("authorizationCache");
		this.userCache = cacheManager.getCache("userCache");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = this.authorizationCache.get(username);
		if(authorizationInfo == null){
			authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.setRoles(sysUserService.findRoles(username));
			authorizationInfo.setStringPermissions(sysUserService.findPermissions(username));
			this.authorizationCache.put(username, authorizationInfo);
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();
		
		SysUser user = this.userCache.get(username);
		if(user == null)
			user = sysUserService.findByUsername(username);

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Boolean.TRUE.equals(user.getLockFlag() == 1 ? true : false)) {
			throw new LockedAccountException(); // 帐号锁定
		}

		if(this.userCache.get(username) == null)
			this.userCache.put(username, user);
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
