package com.saas.framework.utils;

import java.util.regex.Pattern;

public class RegexUtils {

	public static boolean phone(String phone) {
		String pattern = "1[0-9]\\d{9}$";
		return Pattern.matches(pattern, phone);

	}

}
