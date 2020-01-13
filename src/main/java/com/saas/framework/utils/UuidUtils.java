package com.saas.framework.utils;

import java.util.UUID;

public class UuidUtils {

	private static String random32Str() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 随机产生一个32位的字符串
	 * 
	 * @author Moon Yang
	 * @since 2019-12-23
	 * @return
	 */
	public static String createID() {
		return random32Str();
	}

}
