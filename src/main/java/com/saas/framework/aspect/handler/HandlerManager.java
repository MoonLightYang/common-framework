package com.saas.framework.aspect.handler;

import org.springframework.stereotype.Component;

import com.saas.framework.aspect.params.HandlerParamters;

@Component
public class HandlerManager implements IRestHandler {

	@Override
	public void beforeHandler() {
		for (IRestHandler handler : handlers) {
			handler.beforeHandler();
		}
	}

	@Override
	public Object aroundHandler(HandlerParamters params) {
		Object result = null;
		for (IRestHandler handler : handlers) {
			result = handler.aroundHandler(params);
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Integer compare() {
		return null;
	}

}
