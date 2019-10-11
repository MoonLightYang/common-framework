package com.saas.framework.enumer.handler;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class EnumUtils2 {

	public static List<Class<?>> getAllAssignedClass(Class<?> superClass) throws Exception {
		ResourcePatternResolver resover = new PathMatchingResourcePatternResolver();
		// 这里特别注意一下类路径必须这样写
		// 获取指定包下的所有类
		String scan = "classpath*:com/saas/**/enumer/*.class";
		Resource[] resources = resover.getResources(scan);
		for(Resource resource : resources) {
			resource.getURL();
		}
		
		List<Class<?>> classes = getClasses(superClass);
		if (classes == null)
			return null;

		for (Class<?> c : classes) {
			if (superClass.isAssignableFrom(c) && !superClass.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	private static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException {
		String pk = cls.getPackage().getName();
		String path = pk.replace(".", "/");
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource(path);
		return getClasses(new File(url.getFile()), pk);
	}
	
	private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!dir.exists()) {
			return classes;
		}
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				classes.addAll(getClasses(file, pk + "." + file.getName()));
			}
			String fileName = file.getName();
			if (fileName.endsWith(".class")) {
				classes.add(Class.forName(pk + "." + fileName.substring(0, fileName.length() - 6)));
			}
		}
		return classes;
	}

//	public static void main(String[] args) throws ClassNotFoundException {
//		for (Class<?> c : getAllAssignedClass(IEnum.class)) {
//			System.out.println(c);
//		}
//	}
}
