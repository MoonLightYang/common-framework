package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParams;
import com.saas.framework.cache.RedisService;
import com.saas.framework.session.TokenUser;
import com.saas.framework.utils.JsonUtils;

@Component
public class LoggerHandler implements IRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(LoggerHandler.class);

	@Autowired
	RedisService redisService;

	private LoggerHandler() {
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
		this.print(params);
		return null;
	}

	private void print(HandlerParams params) {
		String clazz = params.getClassName();
		String method = params.getMethodName();
		Object[] args = params.getArgs();
		TokenUser user = params.getLoginUser();

		String paramsJson = JsonUtils.toJsonString(args[0]);
		if (user != null)
			LOGGER.debug("【控制器】：{}.{} 【用户】{}", clazz, method, paramsJson);
		else
			LOGGER.debug("【控制器】：{}.{} 【游客】{}", clazz, method, paramsJson);
	}
}
