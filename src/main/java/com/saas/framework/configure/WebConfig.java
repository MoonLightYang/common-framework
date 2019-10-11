package com.saas.framework.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.saas.framework.convert.EnumConverterFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private EnumConverterFactory enumConvertFactory;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(enumConvertFactory);
	}
}
