package org.kingtop.util;

import org.aspectj.lang.ProceedingJoinPoint;


/**
 * AOP中会使用到的方法
 * @author Administrator
 *
 */
public class AspectUtil {

	/**
	 * aop方法中，@Around标识的方法，通过ProceedingJoinPoint.getStaticPart().toString()获取到方法</br>
	 * 处理方法的字符串，获取指定位置参数是不是基础类型，是的话，返回基础类型，否则返回null
	 * @param pjp 
	 * @param index 索引
	 * @return 基础类型或null
	 */
	public static Class getClassByMethodString(ProceedingJoinPoint pjp,int index){
		String nameLabel = pjp.getStaticPart().toString();
		int startIndex = nameLabel.lastIndexOf("(") + 1;
		int endIndex = nameLabel.indexOf(")");
		nameLabel = nameLabel.substring(startIndex, endIndex);
		String[] classNames = nameLabel.split(",");
		if(classNames.length == 0 || index >= classNames.length)
			return null;
		else{
			String className = classNames[index];
			if("int".equals(className))
				return int.class;
			else if("long".equals(className))
				return long.class;
			else if("double".equals(className))
				return double.class;
			else if("float".equals(className))
				return float.class;
			else if("boolean".equals(className))
				return boolean.class;
			else if("char".equals(className))
				return char.class;
			else if("short".equals(className))
				return short.class;
			else if("byte".equals(className))
				return byte.class;
		}
		return null;
	}
	
}
