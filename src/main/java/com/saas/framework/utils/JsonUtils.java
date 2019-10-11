package com.saas.framework.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.framework.exception.SaasException;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJsonString(Object object) {
		if (object == null)
			return null;

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString))
			throw new SaasException("jsonString is empty");

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static <T> List<T> toListObject() {
		return null;
	}
}
