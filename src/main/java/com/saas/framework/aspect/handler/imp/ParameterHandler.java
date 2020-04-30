package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParams;
import com.saas.framework.cache.RedisService;
import com.saas.framework.params.SuperParam;
import com.saas.framework.session.TokenUser;

@Component
public class ParameterHandler implements IRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(ParameterHandler.class);

	@Autowired
	RedisService redisService;

	private ParameterHandler() {
		handlers.add(this);
		if (handlers.size() == HANDLER_SIZE) {
			Collections.sort(handlers, compare);
		}
	}

	@Override
	public Integer compare() {
		return 1;
	}

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		this.aspectParams(params);
		return null;
	}

	private void aspectParams(HandlerParams params) {
		Object[] args = params.getArgs();
		Object arg1 = args[0];
		SuperParam superParam = (SuperParam) arg1;
		
		this.guestParamsHandle(superParam, params);

		TokenUser user = params.getLoginUser();
		if (user == null)
			return;
		this.userParamsHandle(superParam, user);
	}

	private void guestParamsHandle(SuperParam superParam, HandlerParams params) {
		superParam.setIp(params.getIp());
	}

	private void userParamsHandle(SuperParam superParam, TokenUser user) {
		superParam.setHandler(user.getHandler());
		superParam.setHandlerNo(user.getHandlerNo());
		superParam.setEnterpriseId(user.getEnterpriseId());
		superParam.setToken(user.getToken());
	}

}
