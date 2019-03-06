package com.xfh.bingo.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/6 18:47
 */
@Slf4j
public class BeanUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     *
     * @param sourceBean 被提取的对象bean
     * @param targetBean 用于合并的对象bean
     * @return targetBean, 合并后的对象
     */
    public static void combineBean(Object sourceBean, Object targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null) && !"serialVersionUID".equals(sourceField.getName().toString())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失phoneNo败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map convertBean(Object bean) {
        try {
            Class type = bean.getClass();
            Map<String, String> returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result == null) {
                        returnMap.put(propertyName, "");
                    } else {
                        returnMap.put(propertyName, String.valueOf(result));
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            LOGGER.info("实体类字段转换成string出错");
            return null;
        }
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失phoneNo败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map convertBeanNoString(Object bean) {
        try {
            Class type = bean.getClass();
            Map<String, Object> returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            LOGGER.info("实体类字段转换成string出错");
            return null;
        }
    }

    public static Map<String, Object> convertNumberToString(Object bean) {
        try {
            Class type = bean.getClass();
            Field[] fields = type.getDeclaredFields();
            Map<String, Object> returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    Class<?> propertyType = descriptor.getPropertyType();
                    if (Number.class.isAssignableFrom(propertyType)) {
                        if (result != null) {
                            result = String.valueOf(result);
                        }
                    }
                    returnMap.put(propertyName, result);
                }
            }
            return returnMap;
        } catch (Exception e) {
            LOGGER.info("primitive类型转换成string出错");
            return null;
        }
    }


    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */

    public static Object convertMap(Class type, Map map)
            throws Exception {
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
        wrapper.setAutoGrowNestedPaths(true);
        wrapper.setPropertyValues(map);
        return obj;
    }

    /**
     * @param map        参数map
     * @param isNullCalc 是否对空值计算
     * @description 生成排序map
     * @author zhangyanglei
     * @date 2017/10/29 14:22
     */
    public static Map<String, String> fieldSort(Map<String, String> map, boolean isNullCalc) {
        Map<String, String> result = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String string1, String string2) {
                return string1.compareTo(string2);
            }
        });
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            value = (value == null ? "" : String.valueOf(value));
            if (!isNullCalc) {
                if (StringUtils.isBlank(value)) {
                    continue;
                }
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 将实体类转换为map对象
     *
     * @param object
     * @return
     */
    public static Map<String, String> objectToMap(Object object) {
        Map<String, String> result = new HashMap<>();
        Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(object), Map.class);
        map.forEach((k, v) -> {
            String value = v.toString();
            result.put(k, value);
        });
        return result;
    }


    /**
     * 将实体类转换为map对象 并过滤空元素
     *
     * @param object
     * @return
     */
    public static Map<String, String> objectToMapWithoutBlankValue(Object object) {
        Map<String, String> result = new HashMap<>();
        Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(object), Map.class);
        map.forEach((k, v) -> {
            String value = v.toString();
            if (StringUtils.isNotBlank(value)) {
                result.put(k, value);
            }
        });
        return result;
    }

    /**
     * 使用反射机制，调用 private 或 protected 的方法.
     *
     * @param obj        如果是class，则执行静态方法
     * @param name       方法名
     * @param paramTypes 方法参数类型的列表
     * @param args       调用方法的参数列表
     * @return
     * @author jiangfeng 2018/8/17 11:20
     * <pre>示例：<code>
     * // 调用没有参数的方法 foo
     * BeanUtils.invokeMethod( foobar, "foo", null, null )
     * // 使用java调用有一个int类型参数的静态方法 bar
     * BeanUtils.invokeMethod( Foobar.class, "bar", (List) Arrays.asList(Integer.class), (List) Arrays.asList(200))
     * </code></pre>
     */
    public static Object invokeMethod(Object obj, String name, List<Class> paramTypes, List<Object> args) {
        Method method = null;
        Class objClazz = (obj instanceof Class) ? (Class) obj : obj.getClass();
        if (paramTypes == null) {
            method = ReflectionUtils.findMethod(objClazz, name);
        } else {
            method = ReflectionUtils.findMethod(objClazz, name, paramTypes.toArray(new Class[paramTypes.size()]));
        }
        if (method == null) {
            throw new IllegalArgumentException("Method " + name + " doesn't exist.");
        }
        ReflectionUtils.makeAccessible(method);
        if (args == null) {
            return ReflectionUtils.invokeMethod(method, obj);
        } else {
            return ReflectionUtils.invokeMethod(method, obj, args.toArray(new Object[args.size()]));
        }
    }
    /*
     * Description: 该方法用于对空值的映射
     *
     * @param: objectAim
     * @param: objectFrom
     * @return:
     * @auther: xfh
     * @date: 2018/12/7 10:48
     */
    public static Object modify(Object objectAim,Object objectFrom) {

        try{
            for(Field fieldInfo : objectAim.getClass().getDeclaredFields()){
                for (Field field : objectFrom.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    fieldInfo.setAccessible(true);
                    System.out.println(field.getName() + ":" + field.get(objectFrom) );
                    if(field.get(objectFrom) != null &&field.getName() == fieldInfo.getName()){
                        fieldInfo.set(objectAim,field.get(objectFrom));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("实体类映射出错");
            return null;
        }
        return objectAim;
    }

    /*
     * Description: todo
     *
     * @param:
     * @return:
     * @auther: xfh
     * @date: 2018/12/7 14:19
     */
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    private void modify(Object T,String table,Long id){
        LinkedHashMap linkedHashMap = Maps.newLinkedHashMap();
        try{
            for (Field field : T.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field.getName() + ":" + field.get(T) );
                if(field.get(T) != null){
                    linkedHashMap.put(field.getName(),field.get(T));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String sql = " update "+ table+" h set ";
        Iterator iter = linkedHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            sql+= "h."+key+ "="+val+",";
        }
        sql=sql.substring(0,sql.length()-2)+" where h.id="+id;
        Query query = entityManager.createNativeQuery(sql);
        //query.executeUpdate()
    }
}
