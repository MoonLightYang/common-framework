package com.saas.framework.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.enumer.IEnum;

@Component
public class EnumConverterFactory implements ConverterFactory<String, IEnum> {

	@Override
	public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToIEum<>(targetType);
	}

	private static class StringToIEum<T extends IEnum> implements Converter<String, T> {
		private Class<T> targerType;

		public StringToIEum(Class<T> targerType) {
			this.targerType = targerType;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T convert(String source) {
			if (StringUtils.isEmpty(source)) {
				return null;
			}
			return (T) EnumConverterFactory.getIEnum(this.targerType, source);
		}
	}

	public static <T extends IEnum> Object getIEnum(Class<T> targerType, String source) {
		for (T enumObj : targerType.getEnumConstants()) {
			if (source.equals(String.valueOf(enumObj.getValue()))) {
				return enumObj;
			}
		}
		return null;
	}

}
