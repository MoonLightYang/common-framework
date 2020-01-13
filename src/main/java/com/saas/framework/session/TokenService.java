package com.saas.framework.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.saas.framework.aspect.handler.HandlerParams;
import com.saas.framework.cache.RedisService;
import com.saas.framework.exception.SaasException;
import com.saas.framework.utils.JsonUtils;
import com.saas.framework.utils.UuidUtils;

@Service
public class TokenService {

	@Autowired
	RedisService redisService;

	public void refreshSession(HandlerParams params) {
		String loginKey = params.getLoginKey();
		redisService.expire(loginKey, SessionConst.SESSION_EXPIRE);

	}

	public TokenUser unSerializer(String loginRediskey) {
		if (StringUtils.isEmpty(loginRediskey))
			throw new SaasException("无效登陆访问...");

		String tokenUserJson = redisService.get(loginRediskey);
		return JsonUtils.toObject(tokenUserJson, TokenUser.class);
	}

	public String serializer(TokenUser tokenUser) {
		String token = UuidUtils.createID();
		tokenUser.setToken(token);
		String key = SessionConst.PREFIX_TOKEN + token;
		String value = JsonUtils.toJsonString(tokenUser);
		redisService.set(key, value, SessionConst.SESSION_EXPIRE);
		return token;
	}

	public void logout(String token) {
		String key = SessionConst.PREFIX_TOKEN + token;
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

}
