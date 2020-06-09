package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParamters;
import com.saas.framework.session.SessionService;
import com.saas.framework.session.SessionUser;

@Component
public class InjectSessionHandler implements IRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(InjectSessionHandler.class);

	@Autowired
	SessionService sessionService;

	private InjectSessionHandler() {
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
	public Object aroundHandler(HandlerParamters params) {
		SessionUser requestSession = params.getSession();
		String token = requestSession.getToken();
		if (StringUtils.isEmpty(token))
			return null;

		SessionUser serverSession = sessionService.unserializer(token);
		if (serverSession == null) {
			requestSession.setToken(null);
			return null;
		}
		params.setSession(serverSession);
		sessionService.refreshSession(token);
		return null;
	}

}
