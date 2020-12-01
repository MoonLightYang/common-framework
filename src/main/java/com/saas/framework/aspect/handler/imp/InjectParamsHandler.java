package com.saas.framework.aspect.handler.imp;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.aspect.handler.IRestHandler;
import com.saas.framework.aspect.params.HandlerParamters;
import com.saas.framework.aspect.params.ProcessParams;
import com.saas.framework.aspect.params.RequestParams;
import com.saas.framework.params.SuperParams;
import com.saas.framework.session.SessionService;
import com.saas.framework.session.SessionUser;
import com.saas.framework.utils.JsonUtils;

@Component
public class InjectParamsHandler implements IRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(InjectParamsHandler.class);

	@Autowired
	SessionService sessionService;

	private InjectParamsHandler() {
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
	public Object aroundHandler(HandlerParamters params) {
		ProcessParams processParams = params.getProcess();
		SessionUser session = params.getSession();
		RequestParams request = params.getRequest();

		Object[] args = processParams.getArgs();
		Object arg1 = args[0];
		if (arg1 instanceof SuperParams) {
			SuperParams superParam = (SuperParams) arg1;
			this.injectRequest(superParam, request);
			this.injectSession(superParam, session);
			this.print(processParams, session);
			return null;
		}
		this.print(processParams, session);

		return null;
	}

	private void injectRequest(SuperParams superParam, RequestParams request) {
		superParam.setIp(request.getIp());
	}

	private void injectSession(SuperParams superParam, SessionUser session) {
		String token = session.getToken();
		if (StringUtils.isEmpty(token))
			return;

		superParam.setHandler(session.getHandler());
		superParam.setHandlerNo(session.getHandlerNo());
		superParam.setEnterpriseId(session.getEnterpriseId());
		superParam.setEnterprise(session.getEnterprise());
		superParam.setToken(session.getToken());
	}

	private void print(ProcessParams processParams, SessionUser session) {
		String clazz = processParams.getClassName();
		String method = processParams.getMethodName();
		Object[] args = processParams.getArgs();

		String paramsJson = JsonUtils.toJsonString(args[0]);
		String token = session.getToken();
		if (StringUtils.isEmpty(token))
			LOGGER.debug("【控制器】：{}.{} 【游客】{}", clazz, method, paramsJson);
		else
			LOGGER.debug("【控制器】：{}.{} 【用户】{}", clazz, method, paramsJson);
	}
}
