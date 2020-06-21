package com.saas.framework.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 对象属性操作，用于各种VO之间的拷贝
 * 
 * @author Moon Yang
 * @since 2018-12-29
 */

public class CopyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CopyUtils.class);

	/**
	 * 对象之间的属性拷贝，同类型的属性名之间进行拷贝
	 * 
	 * @author Moon Yang
	 * @since 2018-12-29
	 * @param dest 目标对象（拷贝的结果放入此对象）
	 * @param orig 源对象（被拷贝对象）
	 */
	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
			
		} catch (Exception e) {
			LOGGER.error("信息拷贝异常: ", e);
		}
	}

}