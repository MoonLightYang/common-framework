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
		String scan = "classpath*:com/saas/**/enumer/*.class";
		Resource[] resources = resover.getResources(scan);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		Class<?> iEnumClass = IEnum.class;
		for (Resource resource : resources) {
			String name = resource.getURL().toString();
			String[] pathArr = name.split("classes/");
			if(pathArr.length < 2)
				pathArr = name.split("!/");
			
			String path = pathArr[1].replace("/", ".").replace(".class", "");
			Class<?> clazz = Class.forName(path, true, classloader);
			
			if (iEnumClass.isAssignableFrom(clazz) && iEnumClass != clazz) {
				classes.add(clazz);
			}
		}
		return classes;
	}

	public static void main(String[] args) {
		
	}
}
