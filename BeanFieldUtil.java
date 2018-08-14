package com.zgqb.loan.app.util.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**
 * 对字段进行操作
 * 
 * @author ruihua.qin
 *
 */
public class BeanFieldUtil {

	/**
	 * 对字段进行trim操作
	 * 
	 * 		仅对String类型的字段进行trim操作
	 * 		
	 * 		参考org.springframework.beans.BeanUtils.copyProperties()
	 * BeanUtils
	 * @param bean
	 */
	public static void trim(Object bean) {
		Assert.notNull(bean, "Bean must not be null");

		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			Method readMethod = targetPd.getReadMethod();
			if (readMethod != null && "java.lang.String".equals(readMethod.getReturnType().getName()) &&
					ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
				try {
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					String value = (String) readMethod.invoke(bean);
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					writeMethod.invoke(bean, StringUtils.trimAllWhitespace(value));
				}
				catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not trim property '" + targetPd.getName() + "' of this Bean", ex);
				}
			}
		}
	}

	
	/**
	 * 对字段进行trim操作<br><br>
	 * 
	 * 		仅对String类型的字段进行trim操作<br><br>
	 * 		
	 * 		参考org.springframework.beans.BeanUtils.copyProperties()
	 * @param bean
	 * @param doProperties	需要Trim的字段
	 */
	public static void trim(Object bean, String... doProperties) {
		Assert.notNull(bean, "Source must not be null");

		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> doList = (doProperties != null ? Arrays.asList(doProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			if(doList!= null && !doList.contains(targetPd.getName())) {
				//忽略字段
				continue;
			}
			Method writeMethod = targetPd.getWriteMethod();
			Method readMethod = targetPd.getReadMethod();
			if (readMethod != null && "java.lang.String".equals(readMethod.getReturnType().getName()) &&
					ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
				try {
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					String value = (String) readMethod.invoke(bean);
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					//writeMethod.invoke(bean, StringUtils.trimAllWhitespace(value));
					writeMethod.invoke(bean, StringUtils.trimWhitespace(value));
				}
				catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not trim property '" + targetPd.getName() + "' of this Bean", ex);
				}
			}
		}
	}
	
	/**
	 * 对字段进行trim操作
	 * 
	 * 		仅对String类型的字段进行trim操作
	 * 		
	 * 		参考org.springframework.beans.BeanUtils.copyProperties()
	 * @param bean
	 * @param ignoreProperties	忽略的字段
	 */
	public static void trimIgnore(Object bean, String... ignoreProperties) {
		Assert.notNull(bean, "Source must not be null");

		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			if(ignoreList!= null && ignoreList.contains(targetPd.getName())) {
				//忽略字段
				continue;
			}
			Method writeMethod = targetPd.getWriteMethod();
			Method readMethod = targetPd.getReadMethod();
			if (readMethod != null && "java.lang.String".equals(readMethod.getReturnType().getName()) &&
					ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
				try {
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					String value = (String) readMethod.invoke(bean);
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					writeMethod.invoke(bean, StringUtils.trimAllWhitespace(value));
				}
				catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not trim property '" + targetPd.getName() + "' of this Bean", ex);
				}
			}
		}
	}

	/**
	 * 是否包含空字段<br><br>
	 * 
	 * 只要有一个为空即返回true
	 * 
	 * @param bean
	 * @param doProperties
	 */
	public static boolean hasEmpty(Object bean, String... doProperties) {
		Assert.notNull(bean, "Source must not be null");

		Class<?> actualEditable = bean.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> doList = Lists.newArrayList(doProperties);

		for (PropertyDescriptor targetPd : targetPds) {
			if(!doList.contains(targetPd.getName())) {
				//忽略字段
				continue;
			}
			Method writeMethod = targetPd.getWriteMethod();
			Method readMethod = targetPd.getReadMethod();
			if (readMethod != null && "java.lang.String".equals(readMethod.getReturnType().getName()) &&
					ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
				try {
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					String value = (String) readMethod.invoke(bean);
					if(StringUtils.isEmpty(value)) {
						return true;	//含空
					}
				}
				catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not hasEmpty property '" + targetPd.getName() + "' of this Bean", ex);
				}
			}
		}
		//不含空
		return false;
	}

	/**
	 * 对不为空的字段复制到目标Bean上<br><br>
	 * 
	 * 关于“不为空”的定义如下：<br>
	 * 	String类型：	!StringUtils.isEmpty()<br>
	 * 	其他类型的:		!=null<br>
	 * 
	 * @param source		源Bean
	 * @param source		目标Bean
	 * @param doProperties	字段列表
	 */
	public static void copyNotEmpty(Object source, Object target, String... doProperties) {
		Assert.notNull(source, "Source must not be null");

		PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
		List<String> doList = (doProperties != null ? Arrays.asList(doProperties) : null);


		for (PropertyDescriptor sourcePd : sourcePds) {
			if(!doList.contains(sourcePd.getName())) {
				//忽略字段
				continue;
			}

			Method readMethod = sourcePd.getReadMethod();
			
			PropertyDescriptor targetPd = null;
			for(int i=0; i<targetPds.length; i++) {
				if(sourcePd.getName().equals(targetPds[i].getName())) {
					targetPd = targetPds[i];
					break;
				}
			}
			
			if (sourcePd == null || targetPd == null) {
				continue;
			}
			
			Method writeMethod = targetPd.getWriteMethod();
			if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
				try {
					if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
						readMethod.setAccessible(true);
					}
					
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					
					if("java.lang.String".equals(readMethod.getReturnType().getName())) {
						//字符串
						String value = (String) readMethod.invoke(source);
						if(!StringUtils.isEmpty(value)) {
							writeMethod.invoke(target, value);
						}
					} else {
						//其他对象
						Object value = readMethod.invoke(source);
						if(value != null) {
							writeMethod.invoke(target, value);
						}
					}
					
				} catch (Throwable ex) {
					throw new FatalBeanException(
							"Could not copyNotEmpty property '" + targetPd.getName() + "' of this Bean", ex);
				}
				
			}
		}
	}
	/*
	public static void main(String[] args) {
		TxnField t1 = new TxnField();
		t1.setName("name");
		t1.setField("field");
		t1.setType(2);
		t1.setSystemId(123);

		TxnField t2 = new TxnField();
		copyNotEmpty(t1, t2, "name", "field", "type", "systemId", "");
		System.out.println(t2);
	}*/
}
