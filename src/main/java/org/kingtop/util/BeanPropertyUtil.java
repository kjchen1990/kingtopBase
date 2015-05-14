package org.kingtop.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
* 主要用于hibernate中createSQLQuery，将结果列表转换成bean
* @author Administrator
*
*/
public class BeanPropertyUtil {

     /**
     * 将createSQLQuery查询到的map类型的list转成bean的list
     * @param outModelClass bean的类型
     * @param list 数据
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
     public static<T> List<T> converMapToBean(Class<T> outModelClass, List<Map<String, Object>> list) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
          List<T> resultList = new ArrayList<T>();
          for (Map<String, Object> map : list) {
               T entity = outModelClass.newInstance();
               PropertyDescriptor pds[] = BeanUtils.getPropertyDescriptors(outModelClass);
               PropertyDescriptor apropertydescriptor[] = pds;
               int i = apropertydescriptor.length;
               for(int j = 0; j < i; j++)
               {
                    PropertyDescriptor pd = apropertydescriptor[j];
                    if(pd.getWriteMethod() == null)
                         continue;
                    String underscoredName = underscoreName(pd.getName());
                    Object value = map.get(underscoredName.toUpperCase());
                    if(value == null)
                         value = map.get(pd.getName().toUpperCase());
                    if(value != null){
                         if(value instanceof BigDecimal)
                              value = Long.valueOf(value.toString());
                         Object[] args = new Object[1];
                         Class[] types = pd.getWriteMethod().getParameterTypes();
                         String targetClassName = types[0].getName();
                         if("java.lang.Long".equals(targetClassName))
                        	 args[0] = Long.valueOf(value.toString());
                         else
                        	 args[0] = value;
                         pd.getWriteMethod().invoke(entity, args);
                    }
               }
               resultList.add(entity);
          }
          return resultList;
     }
    
     private static String underscoreName(String name)
    {
        if(!StringUtils.hasLength(name))
            return "";
        StringBuilder result = new StringBuilder();
        result.append(name.substring(0, 1).toLowerCase());
        for(int i = 1; i < name.length(); i++)
        {
            String s = name.substring(i, i + 1);
            String slc = s.toLowerCase();
            if(!s.equals(slc))
                result.append("_").append(slc);
            else
                result.append(s);
        }

        return result.toString();
    }
    
}