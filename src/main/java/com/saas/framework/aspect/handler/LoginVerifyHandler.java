package com.saas.framework.aspect.handler;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.cache.RedisService;

@Component
public class LoginVerifyHandler implements IRestHandler {

	@Autowired
	RedisService redisService;

	private LoginVerifyHandler() {
		handlers.add(this);
		if (handlers.size() == HANDLER_SIZE) {
			Collections.sort(handlers, compare);
		}
	}

	@Override
	public Integer compare() {
		return 2;
	}

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
//		Method method = params.getMethod();
//		LoginSign login = method.getAnnotation(LoginSign.class);
//		if (login == null)
//			return null;
//
//		String token = params.getToken();
//		if (StringUtils.isEmpty(token))
//			return RestEntity.EXPIRE();
//
//		String loginKey = params.getLoginKey();
//		String loginUserJson = redisService.get(loginKey);
//		if (StringUtils.isEmpty(loginUserJson))
//			return RestEntity.EXPIRE();
		return null;
	}

}
