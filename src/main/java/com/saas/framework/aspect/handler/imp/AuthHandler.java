package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParams;
import com.saas.framework.cache.RedisService;

@Component
public class AuthHandler implements IRestHandler {

	private AuthHandler() {
		handlers.add(this);
		if (handlers.size() == HANDLER_SIZE) {
			Collections.sort(handlers, compare);
		}
	}

	@Autowired
	RedisService redisService;

	@Override
	public void beforeHandler() {
		handlers.add(this);
	}

	public Integer compare() {
		return 4;
	}

	@Override
	public Object aroundHandler(HandlerParams params) {
//		AuthSign as = params.getMethod().getAnnotation(AuthSign.class);
//		if (as == null)
//			return null;
//
//		// 1: verify if user logined
//		String loginKey = params.getLoginKey();
//		if (StringUtils.isEmpty(loginKey))
//			return RestEntity.EXPIRE();
//
//		String loginUserJson = redisService.get(loginKey);
//		if (StringUtils.isEmpty(loginUserJson))
//			return RestEntity.EXPIRE();
//
//		TokenUser user = params.getLoginUser();
//		params.setLoginUser(user);
//
//		String authKey = params.getAuthKey();
//		if (StringUtils.isEmpty(authKey))
//			return RestEntity.FORBIDDEN();
//
//		String value = params.getUri();
//		boolean flag = redisService.hasSetValue(authKey, value);
//
//		// 2: verify user's auth
//		if (!flag)
//			return RestEntity.FORBIDDEN();

		return null;
	}

}
