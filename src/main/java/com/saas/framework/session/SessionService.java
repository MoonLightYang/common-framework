package com.saas.framework.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.saas.framework.cache.RedisService;
import com.saas.framework.utils.JsonUtils;
import com.saas.framework.utils.UuidUtils;

@Service
public class SessionService {

	@Autowired
	RedisService redisService;

	public void refreshSession(String token) {
		if (StringUtils.isEmpty(token))
			return;

		redisService.expire(token, SessionConst.SESSION_EXPIRE_MINUTES);
	}

	public SessionUser unserializer(String token) {
		if (StringUtils.isEmpty(token))
			return null;

		String key = this.getRedisTokenKey(token);
		String value = redisService.get(key);
		if (StringUtils.isEmpty(value))
			return null;

		return JsonUtils.toObject(value, SessionUser.class);
	}

	public String serializer(SessionUser sessionUser) {
		String token = UuidUtils.createID();
		sessionUser.setToken(token);
		String key = this.getRedisTokenKey(token);
		String value = JsonUtils.toJsonString(sessionUser);
		redisService.set(key, value, SessionConst.SESSION_EXPIRE_MINUTES);
		return token;
	}

	public void logout(String token) {
		String key = this.getRedisTokenKey(token);
		redisService.delete(key);
	}

	/**
	 * 获取登陆的标识
	 * 
	 * @param token
	 * @return
	 */
	public String loginRedisKey(String token) {
		StringBuilder sb = new StringBuilder(50);
		sb.append(SessionConst.PREFIX_TOKEN);
		sb.append(token);
		return sb.toString();
	}

	/**
	 * 获取权限认证的标识
	 * 
	 * @param token
	 * @return
	 */
	public String authRedisKey(String token) {
		StringBuilder sb = new StringBuilder(50);
		sb.append(SessionConst.PREFIX_AUTH);
		sb.append(token);
		return sb.toString();
	}

	private String getRedisTokenKey(String token) {
		if (StringUtils.isEmpty(token))
			return null;

		StringBuilder sb = new StringBuilder(50);
		sb.append(SessionConst.PREFIX_TOKEN).append(token);

		return sb.toString();
	}

}
