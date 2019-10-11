package com.saas.framework.utils;

import java.util.UUID;

public class UuidUtils {

	private static String random32Str() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String createID() {
		return random32Str();
	}

}
