package com.saas.framework.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.saas.framework.aspect.handler.HandlerParams;
import com.saas.framework.session.SessionConst;

@Aspect
@Component
public class RestHandlerAop {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HandlerManager handlerManager;

	public RestHandlerAop() {

	}

	@Around(value = "@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
	public Object userInject(ProceedingJoinPoint point) throws Throwable {

		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();

		ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = atts.getRequest();
		HttpServletResponse response = atts.getResponse();

		HandlerParams params = new HandlerParams();
		params.setRequest(request);
		params.setResponse(response);
		params.setMethod(targetMethod);
		params.setMethodName(methodSignature.getName());
		params.setArgs(point.getArgs());
		params.setPoint(point);

		String clazzStr = point.getTarget().toString();
		String clazz = clazzStr.substring(0, clazzStr.indexOf("@"));
		params.setClassName(clazz);
		
		String token = request.getHeader(SessionConst.ACCESS_TOKEN);
		String loginKey = SessionConst.PREFIX_TOKEN + token;
		String authKey = SessionConst.PREFIX_AUTH + token;
		params.setToken(token);
		params.setLoginKey(loginKey);
		params.setAuthKey(authKey);
		params.setUri(request.getRequestURI());
		
		Object result = handlerManager.aroundHandler(params);
		if (result != null)
			return result;

		return point.proceed();
	}

}
