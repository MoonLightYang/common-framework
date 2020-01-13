package com.saas.framework.cache;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.saas.framework.exception.SaasException;

import io.lettuce.core.GeoArgs.Unit;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate template;

//	private void setHash(String key) {
//
//		List<String> menus = new ArrayList<>();
//		menus.add("/company/add");
//		menus.add("/company/insert");
//		menus.add("/company/delete");
//		menus.add("/company/find");
//
//		SetOperations<String, String> operate = template.opsForSet();
//		String[] values = menus.toArray(new String[menus.size()]);
//		operate.add(key, values);
//	}

	public boolean hasSetValue(String key, String value) {
		if (StringUtils.isEmpty(key))
			throw new SaasException("Redis Set key is not null");
		if (StringUtils.isEmpty(value))
			throw new SaasException("Redis Set value is not null");

		SetOperations<String, String> operate = template.opsForSet();
		return operate.isMember(key, value);
	}

	/**
	 * 设置某个键值多少分钟过期
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value, int minutes) {
		template.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
	}

	/**
	 * 设置键的过期分钟数
	 *
	 * @param key
	 * @param value
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public void expire(String key, int minutes) {
		template.expire(key, minutes, TimeUnit.MINUTES);
	}

	/**
	 * 获键值
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-22
	 */
	public Set<String> keys(String pattern) {
		return template.keys(pattern);
	}

	/**
	 * 获取值
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public String get(String key) {
		return template.opsForValue().get(key);
	}

	/**
	 * 移除
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public boolean delete(String key) {
		return template.delete(key);
	}

	/**
	 * 移除
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public void delete(Collection<String> keys) {
		template.delete(keys);
	}

	/**
	 * 获取自增序号,如果没有当前key，则初始返回1
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public Integer getAndIncrement(String key) {
		LettuceConnectionFactory factory = (LettuceConnectionFactory) template.getConnectionFactory();
		RedisAtomicInteger atomic = new RedisAtomicInteger(key, factory);
		return atomic.getAndIncrement() + 1;
	}

	
	/**
	 * 自增并设定过期时间
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public Integer incrementExpire(String key, long seconds) {
		LettuceConnectionFactory factory = (LettuceConnectionFactory) template.getConnectionFactory();
		RedisAtomicInteger atomic = new RedisAtomicInteger(key, factory);
		Integer atomicInteger = atomic.getAndIncrement();
		if (atomicInteger == 0) {
			atomic.expire(seconds, TimeUnit.SECONDS);
		}

		return atomicInteger + 1;
	}
	
	
	/**
	 * 主要用于各种自增编号的生成，如果没有当前key，则初始返回1
	 *
	 * @param key
	 * @author Moon Yang
	 * @since 2018-05-18
	 */
	public Integer incrementLoopDay(String key) {
		LettuceConnectionFactory factory = (LettuceConnectionFactory) template.getConnectionFactory();
		RedisAtomicInteger atomic = new RedisAtomicInteger(key, factory);
		Integer atomicInteger = atomic.getAndIncrement();
		if (atomicInteger == 0) {
			Date todayEnd = this.getExpireAt();
			atomic.expireAt(todayEnd);
		}

		return atomicInteger + 1;
	}

	private Date getExpireAt() {
		LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);// 当天零点
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = todayEnd.atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * 检查key是否存在，返回boolean值
	 * 
	 * @param key
	 * @author lizhen
	 * @since 2019-05-28
	 */
	public Boolean hasKey(String key) {
		return template.hasKey(key);
	}

	/**
	 * 设置键值
	 * 
	 * @param key
	 * @param value
	 * @author lizhen
	 * @since 2019-05-28
	 */
	public void setWithoutTime(String key, String value) {
		template.opsForValue().set(key, value);
	}

}
