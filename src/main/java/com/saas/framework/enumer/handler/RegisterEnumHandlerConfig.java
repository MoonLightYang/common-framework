package com.saas.framework.enumer.handler;

import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

import com.saas.framework.enumer.IEnum;

@Component
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {

	@Override
	public void customize(Configuration configuration) {
		try {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			List<Class<?>> clazzs = EnumUtils.getAllAssignedClass(IEnum.class);
			clazzs.forEach((clazz) -> typeHandlerRegistry.register(clazz, GeneralTypeHandler.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
