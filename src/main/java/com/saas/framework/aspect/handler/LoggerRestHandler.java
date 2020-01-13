package com.saas.framework.aspect.handler;

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

import com.saas.framework.annotation.AuthSign;
import com.saas.framework.annotation.LoginSign;
import com.saas.framework.params.SuperParam;
import com.saas.framework.session.SessionConst;
import com.saas.framework.session.TokenService;
import com.saas.framework.session.TokenUser;
import com.saas.framework.utils.JsonUtils;

@Component
public class LoggerRestHandler {

	Logger LOGGER = LoggerFactory.getLogger(LoggerRestHandler.class);

	@Autowired
	TokenService tokenService;

	public HandlerParams process(ProceedingJoinPoint point, HttpServletRequest request, HttpServletResponse response) {
		// 1：返回参数
		HandlerParams params = this.beforeParams(point, request, response);
		this.ipParams(params);

		Method method = params.getMethod();
		String clazzName = params.getClassName();
		String methodName = params.getMethodName();

		// 2：通用参数列表
		LoginSign loginSign = method.getAnnotation(LoginSign.class);
		AuthSign authSign = method.getAnnotation(AuthSign.class);
		if (loginSign == null && authSign == null) {
			this.printLogger(false, clazzName, methodName, point.getArgs());
			return params;
		}

		// 3：权限参数列表
		this.afterParams(params, request);

		// 4: 登陆用户信息
		this.loginParams(params);

		this.printLogger(true, clazzName, methodName, point.getArgs());
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
	private HandlerParams beforeParams(ProceedingJoinPoint point, HttpServletRequest request,
			HttpServletResponse response) {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();

		// 1：基础信息
		HandlerParams params = new HandlerParams();
		params.setRequest(request);
		params.setResponse(response);
		params.setMethod(targetMethod);
		params.setMethodName(methodSignature.getName());
		params.setArgs(point.getArgs());
		params.setPoint(point);

		// 2：类信息获取
		String clazzStr = point.getTarget().toString();
		String clazz = clazzStr.substring(0, clazzStr.indexOf("@"));
		params.setClassName(clazz);
		return params;
	}

	private HandlerParams ipParams(HandlerParams params) {
		Object[] args = params.getArgs();
		if (args == null)
			return null;

		Object arg1 = args[0];
		SuperParam param = (SuperParam) arg1;
		HttpServletRequest request = params.getRequest();

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		params.setIp(ip);

		return params;
	}

	/**
	 * 有权限认证时，还需要加上权限相关参数
	 * 
	 * @param params
	 * @param request
	 */
	private HandlerParams afterParams(HandlerParams params, HttpServletRequest request) {
		String token = request.getHeader(SessionConst.ACCESS_TOKEN);
		params.setToken(token);

		String loginKey = tokenService.loginRedisKey(token);
		params.setLoginKey(loginKey);

		String authKey = tokenService.authRedisKey(token);
		params.setAuthKey(authKey);

		params.setUri(request.getRequestURI());
		return params;
	}

	private HandlerParams loginParams(HandlerParams params) {
		TokenUser user = tokenService.unSerializer(params.getLoginKey());
		params.setLoginUser(user);
		this.aspectParams(params);
		tokenService.refreshSession(params);
		return params;
	}

	private void aspectParams(HandlerParams params) {
		TokenUser user = params.getLoginUser();
		LOGGER.info("token redis：{}", JsonUtils.toJsonString(user));
		if (user == null)
			return;

		Object[] args = params.getArgs();
		Object arg1 = args[0];
		SuperParam superParam = (SuperParam) arg1;

		superParam.setHandler(user.getHandler());
		superParam.setHandlerNo(user.getHandlerNo());
		superParam.setEnterpriseId(user.getEnterpriseId());
		superParam.setToken(user.getToken());
		LOGGER.info("super param：{}", JsonUtils.toJsonString(superParam));
	}

	private void printLogger(boolean login, String clazz, String method, Object[] args) {
		if (args != null && args.length > 0) {
			if (login)
				LOGGER.debug("方法：{}.{}，【用户参数】：{}", clazz, method, JsonUtils.toJsonString(args[0]));
			else
				LOGGER.debug("方法：{}.{}，【游客参数】：{}", clazz, method, JsonUtils.toJsonString(args[0]));
		}
	}

}
