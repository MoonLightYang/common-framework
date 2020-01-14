package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.RestEntity;
import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParams;
import com.saas.framework.cache.RedisService;
import com.saas.framework.session.TokenUser;

@Component
public class LoginHandler implements IRestHandler {

	@Autowired
	RedisService redisService;

	private LoginHandler() {
		handlers.add(this);
		if (handlers.size() == HANDLER_SIZE) {
			Collections.sort(handlers, compare);
		}
	}

	@Override
	public Integer compare() {
		return 3;
	}

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		if(!params.isLoginVeify())
			return null;
		
		TokenUser user = params.getLoginUser();
		if (user == null)
			return RestEntity.EXPIRE();

		return null;
	}

}
