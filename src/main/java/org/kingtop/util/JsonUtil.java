package org.kingtop.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	private static GsonBuilder builder = new GsonBuilder();
	
	/**
	 * 将一个对象转换成字符串，如果属性为Null，则转换的时候忽略该属性
	 * @param json 转换的对象
	 * @return
	 */
	public static String toString(Object json){
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();
		return gson.toJson(json);
	}
	
	/**
	 * 将一个对象转换成字符串，如果属性为Null，则转换的时候忽略该属性
	 * @param obj 转换的对象
	 * @param datePattem 时间格式化
	 * @return
	 */
	public static String toString(Object obj, String datePattem){
		builder.setDateFormat(datePattem);
		Gson gson = builder.create();
		return gson.toJson(obj);
	}
	
	/**
	 * 将一个对象转换成字符串，如果属性为Null，则转换的时候也设置为null
	 * @param object 转换的对象
	 * @return
	 */
	public static String toStringAsNull(Object object)
    {
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.serializeNulls().create();
        return gson.toJson(object);
    }
	
	/**
	 * 将一个对象转换成字符串，如果属性为Null，则转换的时候也设置为null
	 * @param object 转换的对象
	 * @param datePattem 时间格式化
	 * @return
	 */
	public static String toStringAsNull(Object object, String datePattem)
	{
		builder.setDateFormat(datePattem);
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(object);
	}
	
	/**
	 * 将JSON字符串转换成对象
	 * 
	 * @param json json字符串
	 * @param typeOfT 对象类别
	 * @return
	 */
	public static Object fromJson(String json, Type typeOfT){
		Gson gson = builder.create();
		return gson.fromJson(json, typeOfT);
	}
	
	/**
	 * 将JSON字符串转换成对象
	 * 
	 * @param json json字符串
	 * @param typeOfT 对象类别
	 * @param datePattem 时间格式化
	 * @return
	 */
	public static Object fromJson(String json, Type typeOfT, String datePattem){
		builder.setDateFormat(datePattem);
		Gson gson = builder.create();
		return gson.fromJson(json, typeOfT);
	}
	
}
