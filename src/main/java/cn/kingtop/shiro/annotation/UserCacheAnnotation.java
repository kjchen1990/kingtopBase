package cn.kingtop.shiro.annotation;

public @interface UserCacheAnnotation {

	/**
	 * 是否重新缓存用户，true则忽略clearUserCahce和addUserCache的设置
	 * @return
	 */
	boolean restoreUserCache() default false;
	
	/**
	 * 是否清空用户缓存
	 * @return
	 */
	boolean clearUserCahce() default false;
	
	/**
	 * 是否缓存用户信息
	 * @return
	 */
	boolean addUserCache() default false;
	
}
