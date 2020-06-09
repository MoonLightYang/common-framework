package com.saas.framework.aspect.process;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.saas.framework.annotation.Authorition;
import com.saas.framework.annotation.Login;
import com.saas.framework.aspect.params.AnnotationParams;
import com.saas.framework.aspect.params.HandlerParamters;
import com.saas.framework.aspect.params.ProcessParams;
import com.saas.framework.aspect.params.RequestParams;
import com.saas.framework.session.SessionConst;
import com.saas.framework.session.SessionUser;

@Component
public class Processer {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public HandlerParamters process(ProceedingJoinPoint point, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		// 1：请求头相关信息
		RequestParams request = this.requestHandle(servletRequest);

		// 2：处理点相关信息
		ProcessParams process = this.processHandle(point);

		// 3：是否需要登录和权限认证
		AnnotationParams annos = this.loginAuthHandle(process);

		// 4：session信息相关
		SessionUser session = this.sessionHandle(servletRequest);
		return new HandlerParamters(request, process, annos, session);
	}

	private RequestParams requestHandle(HttpServletRequest servletRequest) {
		String ip = this.getIp(servletRequest);
		String uri = servletRequest.getRequestURI();

		RequestParams params = new RequestParams();
		params.setIp(ip);
		params.setUri(uri);
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
	private String getIp(HttpServletRequest request) {
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

		return ip;
	}

	private ProcessParams processHandle(ProceedingJoinPoint point) {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;

		Method method = methodSignature.getMethod();
		String clazzStr = point.getTarget().toString();
		String clazz = clazzStr.substring(0, clazzStr.indexOf("@"));

		ProcessParams params = new ProcessParams();
		params.setMethod(method);
		params.setMethodName(methodSignature.getName());
		params.setArgs(point.getArgs());
		params.setPoint(point);
		params.setClassName(clazz);

		return params;
	}

	/**
	 * 登录或权限验证标识获取
	 * 
	 * @param params
	 * @return
	 */
	private AnnotationParams loginAuthHandle(ProcessParams processPrams) {
		Method method = processPrams.getMethod();
		Login login = method.getAnnotation(Login.class);
		Authorition auth = method.getAnnotation(Authorition.class);

		boolean isLogin = login != null ? true : false;
		boolean isAuth = auth != null ? true : false;
		AnnotationParams params = new AnnotationParams(isLogin, isAuth);
		return params;
	}

	private SessionUser sessionHandle(HttpServletRequest servletRequest) {
		String token = servletRequest.getHeader(SessionConst.ACCESS_TOKEN);

		SessionUser session = new SessionUser(token);
		return session;
	}
}
