package com.saas.framework.aspect.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.params.SuperParam;
import com.saas.framework.session.TokenUser;

@Component
public class ParamRestHandler implements IRestHandler {
	
	private ParamRestHandler() {
		handlers.add(this);
	}

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		this.initCommonParam(params);
		return null;
	}

	private void initCommonParam(HandlerParams params) {
		TokenUser user = params.getLoginUser();
		if(user == null)
			return;
		
		Object[] args = params.getArgs();
		Object arg1 = args[0];
		SuperParam param = (SuperParam) arg1;
		
		param.setHandler(user.getHandler());
		param.setHandlerNo(user.getHandlerNo());
		param.setEnterpriseId(user.getEnterpriseId());
		param.setToken(user.getToken());
		
		HttpServletRequest request = params.getRequest();
		param.setIpAddress(request.getRemoteAddr());
	}
}
