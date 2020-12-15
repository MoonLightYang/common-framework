package com.saas.framework.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/***
 * 时间与日期工具类
 * 
 * @author Moon Yang
 * @since 2019-11-12
 */
public class TimeUtils {

	private static final DateTimeFormatter FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final DateTimeFormatter FORMATTER_YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");
	private static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	/**
	 * 获取今天的日期字符串yyyyMMdd：返回示例：20191112
	 * 
	 * @since 2019-11-12
	 * @return
	 */
	public static String yyyyMMdd() {
		LocalDateTime localNow = LocalDateTime.now();
		return FORMATTER_YYYYMMDD.format(localNow);
	}

	/**
	 * 获取今天的日期字符串yyyyMMdd：返回示例：20191112
	 * 
	 * @since 2019-11-12
	 * @return
	 */
	public static String yyyyMMdd(LocalDate date) {
		return FORMATTER_YYYYMMDD.format(date);
	}

	/**
	 * 获取今天的日期字符串yyMMdd：返回示例：191112
	 * 
	 * @since 2019-11-12
	 * @return
	 */
	public static String yyMMdd() {
		LocalDateTime localNow = LocalDateTime.now();
		return FORMATTER_YYMMDD.format(localNow);
	}

	/**
	 * 获取当天最后的时间 。示例：23:59:59 23:59:59:9999999
	 * 
	 * @since 2019-11-12
	 * @return
	 */
	public static Date getTodayEndDate() {
		LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = todayEnd.atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * 获取两个时间的时间差值 date1 被减数 date 减数
	 * 
	 * @return 秒
	 */
	public static Long getTimeDiff(Date date1, Date date) {
		long seconds = (date1.getTime() - date.getTime()) / 1000;
		return seconds;
	}

	/**
	 * 获取某一天最后的时间，eg：示例：2019-12-02 23:59:59
	 */
	public static String getEndDate(String time) {
		String s = time + " 23:59:59";
		return s;
	}

	/**
	 * 获取今天的日期字符串yyMMdd：返回示例：191112
	 *
	 * @since 2019-11-12
	 * @return
	 */
	public static String yyyy_MM_dd() {
		LocalDateTime localNow = LocalDateTime.now();
		return FORMATTER_YYYY_MM_DD.format(localNow);
	}
	
	public static int getCurrentMonthStartDate() {
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String s = firstday.format(formatter);
		return Integer.parseInt(s);
	}

	public static int getCurrentMonthEndDate() {
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String s = lastDay.format(formatter);
		return Integer.parseInt(s);
	}

}
