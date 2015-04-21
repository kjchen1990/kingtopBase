package cn.kingtop.shiro.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthCacheAnnotation {
	
	/**
	 * 是否重新缓存角色，ture则忽略restoreRolesCache和addRolesCache
	 * @return
	 */
	boolean restoreRolesCache() default false;

	/**
	 * 是否清空角色的缓存
	 * @return
	 */
	boolean clearRolesCache() default false;
	
	/**
	 * 是否添加角色的缓存
	 * @return
	 */
	boolean addRolesCache() default false;
	
	/**
	 * 是否重新缓存权限，true则忽略clearPermission和addPermission
	 * @return
	 */
	boolean restorePermission() default false;
	
	/**
	 * 是否清空权限的缓存
	 * @return
	 */
	boolean clearPermission() default false;
	
	/**
	 * 是否添加权限的缓存
	 * @return
	 */
	boolean addPermission() default false;
	
}
