package com.saas.framework.aspect.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.cache.RedisService;
import com.saas.framework.params.SuperParam;

@Component
public class GuestHandler extends AbstractAuthVerifyHandler implements IRestHandler {

	private GuestHandler() {
		handlers.add(0, this);
	}

	@Autowired
	RedisService redisService;

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		Object[] args = params.getArgs();
		if(args == null)
			return null;
		
		Object arg1 = args[0];
		SuperParam param = (SuperParam) arg1;
		HttpServletRequest request = params.getRequest();
		param.setIpAddress(request.getRemoteAddr());
		return null;
	}

}
