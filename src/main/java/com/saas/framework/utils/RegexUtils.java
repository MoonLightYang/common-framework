package com.saas.framework.utils;

import java.util.regex.Pattern;

public class RegexUtils {

	public static boolean phone(String phone) {
		String pattern = "^1[\\d]{10}";
		boolean flag = Pattern.matches(pattern, phone);
		return flag;

	}

}
