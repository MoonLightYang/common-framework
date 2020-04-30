package com.saas.framework.aspect.process;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.annotation.AuthSign;
import com.saas.framework.annotation.LoginSign;
import com.saas.framework.aspect.params.HandlerParams;
import com.saas.framework.session.SessionConst;
import com.saas.framework.session.TokenService;
import com.saas.framework.session.TokenUser;

@Component
public class Processer {

	Logger LOGGER = LoggerFactory.getLogger(Processer.class);

	@Autowired
	TokenService tokenService;

	public HandlerParams process(ProceedingJoinPoint point, HttpServletRequest request, HttpServletResponse response) {
		// 1：返回来宾参数参数
		HandlerParams params = this.guestParams(point, request, response);

		// 2：是否需要登录和权限认证
		this.isLoginOrAuth(params);

		// 3：设置redis的token key,如果没有登录则为空
		this.setRedisTokenKey(params, request);

		// 4: 登录用户信息注入
		this.injectUser(params);

		return params;
	}

	/**
	 * 登录或权限验证标识获取
	 * 
	 * @param params
	 * @return
	 */
	private HandlerParams isLoginOrAuth(HandlerParams params) {
		Method method = params.getMethod();
		LoginSign loginSign = method.getAnnotation(LoginSign.class);
		AuthSign authSign = method.getAnnotation(AuthSign.class);
		if (loginSign != null) {
			params.setLoginVeify(true);
		} else if (authSign != null) {
			params.setAuthVeify(true);
		}
		return params;
	}

	/**
	 * 无权限相关的参数
	 * 
	 * @param point
	 * @param request
	 * @param response
	 * @return
	 */
	private HandlerParams guestParams(ProceedingJoinPoint point, HttpServletRequest request,
			HttpServletResponse response) {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		// 类和方法相关信息
		String clazzStr = point.getTarget().toString();
		String clazz = clazzStr.substring(0, clazzStr.indexOf("@"));

		// ip参数
		String ip = this.ipParams(request);

		// 返回来宾参数对象
		HandlerParams params = new HandlerParams();
		params.setUri(request.getRequestURI());
		params.setRequest(request);
		params.setResponse(response);
		params.setMethod(method);
		params.setMethodName(methodSignature.getName());
		params.setArgs(point.getArgs());
		params.setPoint(point);
		params.setClassName(clazz);
		params.setIp(ip);
		
		return params;
	}

	// && !"unknown".equalsIgnoreCase(ip)
	private String ipParams(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!StringUtils.isEmpty(ip))
			return ip;

		ip = request.getHeader("Proxy-Client-IP");
		if (!StringUtils.isEmpty(ip))
			return ip;

		ip = request.getHeader("WL-Proxy-Client-IP");
		if (!StringUtils.isEmpty(ip))
			return ip;

		ip = request.getRemoteAddr();
		if (!StringUtils.isEmpty(ip))
			return ip;

		return null;
	}

	/**
	 * 设置redis对应的权限token key
	 * 
	 * @param params
	 * @param request
	 */
	private HandlerParams setRedisTokenKey(HandlerParams params, HttpServletRequest request) {
		String token = request.getHeader(SessionConst.ACCESS_TOKEN);
		if (StringUtils.isEmpty(token))
			return params;

		params.setToken(token);

		String loginKey = tokenService.loginRedisKey(token);
		params.setLoginKey(loginKey);

		String authKey = tokenService.authRedisKey(token);
		params.setAuthKey(authKey);
		return params;
	}

	private HandlerParams injectUser(HandlerParams params) {
		String loginKey = params.getLoginKey();
		if (StringUtils.isEmpty(loginKey))
			return params;

		TokenUser user = tokenService.unSerializer(params.getLoginKey());
		if (user == null)
			return params;

		// TODO 这个用获取并且重置过期时间
		params.setLoginUser(user);
		user.setIp(params.getIp());
		tokenService.refreshSession(params);
		return params;
	}

}
