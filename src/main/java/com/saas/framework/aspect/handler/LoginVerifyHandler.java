package com.saas.framework.aspect.handler;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.RestEntity;
import com.saas.framework.annotation.LoginSign;
import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.cache.RedisService;
import com.saas.framework.session.TokenUser;

@Component
public class LoginVerifyHandler extends AbstractAuthVerifyHandler implements IRestHandler {

	private LoginVerifyHandler() {
		handlers.add(this);
	}

	@Autowired
	RedisService redisService;

	@Override
	public void beforeHandler() {

	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		Method method = params.getMethod();
		LoginSign login = method.getAnnotation(LoginSign.class);
		if (login == null)
			return null;

		String token = params.getToken();
		if (StringUtils.isEmpty(token))
			return RestEntity.EXPIRE();
		
		String loginKey = params.getLoginKey();
		String loginUserJson = redisService.get(loginKey);
		if(StringUtils.isEmpty(loginUserJson))
			return RestEntity.EXPIRE();
		
		TokenUser user = super.convert(loginUserJson);
		params.setLoginUser(user);
		
		super.refreshToken(redisService, params);
		return null;
	}

}
