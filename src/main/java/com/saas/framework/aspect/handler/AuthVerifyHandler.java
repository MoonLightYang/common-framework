package com.saas.framework.aspect.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.RestEntity;
import com.saas.framework.annotation.AuthSign;
import com.saas.framework.aspect.IRestHandler;
import com.saas.framework.cache.RedisService;
import com.saas.framework.session.LoginToken;

@Component
public class AuthVerifyHandler extends AbstractAuthVerifyHandler implements IRestHandler {

	private AuthVerifyHandler() {
		handlers.add(this);
	}

	@Autowired
	RedisService redisService;

	@Override
	public void beforeHandler() {
		handlers.add(1, this);
	}

	@Override
	public Object aroundHandler(HandlerParams params) {
		AuthSign as = params.getMethod().getAnnotation(AuthSign.class);
		if (as == null)
			return null;

		// 1: verify if user logined
		String loginKey = params.getLoginKey();
		if (StringUtils.isEmpty(loginKey))
			return RestEntity.FAIL().info("登录失效，请先登陆");
		
		String loginUserJson = redisService.get(loginKey);
		if(StringUtils.isEmpty(loginUserJson))
			return RestEntity.FAIL().info("登陆失效，请先登陆");
		
		LoginToken user = super.convert(loginUserJson);
		params.setLoginUser(user);
		
		String authKey = params.getAuthKey();
		if(StringUtils.isEmpty(authKey))
			return RestEntity.FAIL().info("暂无此访问权限");
		
		String value = params.getUri();
		boolean flag = redisService.hasSetValue(authKey, value);

		// 2: verify user's auth
		if (!flag)
			return RestEntity.FAIL().info("暂无此访问权限");
		
		// refresh token
		super.refreshToken(redisService, params);
		return null;
	}

}
