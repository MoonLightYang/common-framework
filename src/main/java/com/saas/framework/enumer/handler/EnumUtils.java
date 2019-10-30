package com.saas.framework.enumer.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.saas.framework.enumer.IEnum;

public class EnumUtils {

	public static List<Class<?>> getAllAssignedClass(Class<?> superClass) throws Exception {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		ResourcePatternResolver resover = new PathMatchingResourcePatternResolver();
		String scan = "classpath*:com/**/enumer/*.class";
		Resource[] resources = resover.getResources(scan);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		Class<?> iEnumClass = IEnum.class;
		for (Resource resource : resources) {
			String path = null;
			String name = resource.getURL().toString();
			String[] pathArr = name.split("classes/");
			if (pathArr.length == 2) {
				path = pathArr[1].replace("/", ".").replace(".class", "");
			}else {
				pathArr = name.split("!/");
				path = pathArr[pathArr.length - 1].replace("/", ".").replace(".class", "");
			}
				
			Class<?> clazz = Class.forName(path, true, classloader);
			if (iEnumClass.isAssignableFrom(clazz) && iEnumClass != clazz) {
				classes.add(clazz);
			}
		}
		return classes;
	}

//	public static void main(String[] args) {
//		String path ="";
//		String name ="/usr/local/saas/test-java/saas-erp-app-0.0.1-SNAPSHOT.jar!/BOOT-INF/lib/saas-erp-basic-0.0.1-SNAPSHOT.jar!/com/saas/basic/enumer/Gender.class";
//		String[] pathArr = name.split("classes/");
//		if (pathArr.length == 2) {
//			path = pathArr[1].replace("/", ".").replace(".class", "");
//		}else {
//			pathArr = name.split("!/");
//			path = pathArr[pathArr.length - 1].replace("/", ".").replace(".class", "");
//		}
//			
//		System.out.println("path : " + path);
//	}
	
}
