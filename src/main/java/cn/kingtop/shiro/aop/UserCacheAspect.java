package cn.kingtop.shiro.aop;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
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
import cn.kingtop.shiro.annotation.UserCacheAnnotation;
import cn.kingtop.sys.model.SysUser;
import cn.kingtop.sys.service.ISysUserService;

@Component
@Aspect
public class UserCacheAspect {
	
	private Cache<String, SysUser> userCache;
	
	private CacheManager cacheManager;

	public CacheManager getCacheManager() {
		return cacheManager;
	}
	@Autowired
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	private ISysUserService sysUserService;

	public ISysUserService getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Pointcut(value = "target(cn.kingtop.sys.service.impl.SysUserServiceImpl) " +
			"&& @annotation(cn.kingtop.shiro.annotation.UserCacheAnnotation)")
	private void serviceCachePointCut(){
	}
	
	/**
	 * 根据UserCacheAnnotation中的声明，重新设置缓存
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@SuppressWarnings("unchecked")
	@Around(value = "serviceCachePointCut()")
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
					
					if(this.userCache == null)
						this.userCache = cacheManager.getCache("userCache");
					
					UserCacheAnnotation userCacheAnnotation = method.getAnnotation(UserCacheAnnotation.class);
					SysUser user = this.userCache.get(username);
					//判断用户信息是否需要清空和缓存
					if(userCacheAnnotation.restoreUserCache()){
						user = sysUserService.findByUsername(username);
					}else{
						if(userCacheAnnotation.clearUserCahce() && user != null)
							this.userCache.put(username, null);
						if(userCacheAnnotation.addUserCache())
							user = sysUserService.findByUsername(username);
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
