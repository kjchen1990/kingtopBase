package cn.kingtop.shiro.aop;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.kingtop.util.AspectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.kingtop.shiro.annotation.AuthCacheAnnotation;
import cn.kingtop.sys.service.ISysUserService;

/**
 * 用户与授权相关的权限缓存
 * @author 陈金波
 *
 */
@Component
@Aspect
public class UserAuthCacheAspect {
	
	private Cache<String, SimpleAuthorizationInfo> authorizationInfoCache = null;
	
	private ISysUserService sysUserService;

	public ISysUserService getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
	private CacheManager cacheManager;

	public CacheManager getCacheManager() {
		return cacheManager;
	}
	@Autowired
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	/**
	 * 授权（Auth）
	 * 当增删改授权时，根据AuthCacheAnnotation判断是否需要重新缓存授权
	 */
	// @Pointcut(value = "target(cn.kingtop.shiro.annotation.AuthCacheAnnotation)")
	// && @annotation(cn.kingtop.shiro.annotation.AuthCacheAnnotation)
	@Pointcut(value = "target(cn.kingtop.sys.service.impl.SysUserServiceImpl) " +
			"&& @annotation(cn.kingtop.shiro.annotation.AuthCacheAnnotation)")
	//@Pointcut(value="target(cn.kingtop.sys.action.SysUserAction) && @annotation(cn.kingtop.shiro.annotation.AuthCacheAnnotation)")
	//@Pointcut(value = "target(cn.kingtop.sys.service.impl.SysUserServiceImpl)")
	private void authServicePointcut() {
	}

	/**
	 * 根据AuthCacheAnnotation中的声明，重新设置缓存
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@SuppressWarnings("unchecked")
	@Around(value = "authServicePointcut()")
	public Object restoreUserAuthCache(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Object result = pjp.proceed();
		try {
			String methodName = pjp.getSignature().getName();
			Class targetClass = pjp.getTarget().getClass();
			Class[] argsClass = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				Class argClass = null;
				if(arg.getClass().getName().equals("java.lang.Integer"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				else if(arg.getClass().getName().equals("java.lang.Long"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				else if(arg.getClass().getName().equals("java.lang.Double"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				else if(arg.getClass().getName().equals("java.lang.Float"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				else if(arg.getClass().getName().equals("java.lang.Boolean"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				else if(arg.getClass().getName().equals("java.lang.Short"))
					argClass = AspectUtil.getClassByMethodString(pjp,i);
				if(argClass == null)
					argClass = arg.getClass();
				argsClass[i] = argClass;
			}
			Method method = targetClass.getMethod(methodName,argsClass);
			if (method != null) {
				boolean hasAnnotation = method.isAnnotationPresent(AuthCacheAnnotation.class);
				if (hasAnnotation) {
					String username = (String) SecurityUtils.getSubject().getPrincipal();
					
					if(authorizationInfoCache == null)
						authorizationInfoCache = cacheManager.getCache("authorizationInfoCache");
					
					AuthCacheAnnotation authCacheAnnotation = method.getAnnotation(AuthCacheAnnotation.class);
					SimpleAuthorizationInfo authorizationInfo = authorizationInfoCache.get(username);
					//判断角色的清空和重新缓存
					if(authCacheAnnotation.restoreRolesCache()){
						if(authorizationInfo == null)
							authorizationInfo = new SimpleAuthorizationInfo();
						authorizationInfo.setRoles(sysUserService.findRoles(username));
						authorizationInfoCache.put(username, authorizationInfo);
					}else{
						if(authCacheAnnotation.clearRolesCache() && authorizationInfo != null){
							authorizationInfo.setRoles(null);
							authorizationInfoCache.put(username, authorizationInfo);
						}
						if(authCacheAnnotation.addRolesCache()){
							if(authorizationInfo == null)
								authorizationInfo = new SimpleAuthorizationInfo();
							authorizationInfo.setRoles(sysUserService.findRoles(username));
							authorizationInfoCache.put(username, authorizationInfo);
						}
					}
					//判断权限的清空和重新缓存
					if(authCacheAnnotation.restorePermission()){
						if(authorizationInfo == null)
							authorizationInfo = new SimpleAuthorizationInfo();
						authorizationInfo.setStringPermissions(sysUserService.findPermissions(username));
						authorizationInfoCache.put(username, authorizationInfo);
					}else{
						if(authCacheAnnotation.clearPermission() && authorizationInfo != null){
							authorizationInfo.setStringPermissions(null);
							authorizationInfoCache.put(username, authorizationInfo);
						}
						if(authCacheAnnotation.addPermission()){
							if(authorizationInfo == null)
								authorizationInfo = new SimpleAuthorizationInfo();
							authorizationInfo.setStringPermissions(sysUserService.findPermissions(username));
							authorizationInfoCache.put(username, authorizationInfo);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
