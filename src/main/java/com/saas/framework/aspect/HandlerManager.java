package com.saas.framework.aspect;

import org.springframework.stereotype.Component;

import com.saas.framework.aspect.handler.HandlerParams;

@Component
public class HandlerManager implements IRestHandler {

	@Override
	public void beforeHandler() {
		for (IRestHandler handler : handlers) {
			handler.beforeHandler();
		}
	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		Object result = null;
		for (IRestHandler handler : handlers) {
			result = handler.aroundHandler(params);
			if(result != null) 
				break;
		}
		return result;
	}

}
