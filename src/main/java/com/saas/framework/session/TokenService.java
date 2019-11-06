package com.saas.framework.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.framework.cache.RedisService;
import com.saas.framework.utils.JsonUtils;
import com.saas.framework.utils.UuidUtils;

@Service
public class TokenService {
	
	@Autowired
	RedisService redisService;
	
	public String serializer(TokenUser tokenUser) {
		String token = UuidUtils.createID();
		String key = SessionConst.PREFIX_TOKEN + token;
		String value = JsonUtils.toJsonString(tokenUser);
		redisService.set(key, value, SessionConst.SESSION_EXPIRE);
		return token;
	}
	
	

}
