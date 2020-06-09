package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.RestEntity;
import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.AnnotationParams;
import com.saas.framework.aspect.params.HandlerParamters;
import com.saas.framework.session.SessionService;
import com.saas.framework.session.SessionUser;

@Component
public class LoginAuthHandler implements IRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(LoginAuthHandler.class);

	private LoginAuthHandler() {
		handlers.add(this);
		if (handlers.size() == HANDLER_SIZE)
			Collections.sort(handlers, compare);
	}

	@Autowired
	SessionService sessionService;

	@Override
	public Integer compare() {
		return 3;
	}

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParamters params) {
		AnnotationParams annos = params.getAnnos();
		if (!annos.isVerifyAuth() && !annos.isVerifyLogin())
			return null;

		SessionUser requestSession = params.getSession();
		String token = requestSession.getToken();
		if (StringUtils.isEmpty(token))
			return RestEntity.UNLOGIN();

		if (annos.isVerifyAuth()) {
			// TODO 验证权限
		}

		return null;
	}

}
