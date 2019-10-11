package com.saas.framework.aspect.handler;

import org.springframework.util.StringUtils;

import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.cache.RedisService;
import com.saas.framework.exception.SaasException;
import com.saas.framework.session.LoginToken;
import com.saas.framework.session.SessionConst;
import com.saas.framework.utils.JsonUtils;

public abstract class AbstractAuthVerifyHandler implements IRestHandler {

	protected void refreshToken(RedisService redis, HandlerParams params) {
		String loginKey = params.getLoginKey();
		String authKey = params.getAuthKey();
		redis.expire(loginKey, SessionConst.SESSION_EXPIRE);
		redis.expire(authKey, SessionConst.SESSION_EXPIRE);
	}

	protected LoginToken convert(String userJson) {
		if (StringUtils.isEmpty(userJson))
			throw new SaasException("Login User Cast Exception...");

		return JsonUtils.toObject(userJson, LoginToken.class);
	}

}
