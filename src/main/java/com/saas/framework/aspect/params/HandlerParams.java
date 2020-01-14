package com.saas.framework.aspect.params;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;

import com.saas.framework.session.TokenUser;

import lombok.Data;

@Data
public class HandlerParams {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProceedingJoinPoint point;
	private Method method;
	private String methodName;
	private String className;
	private Object[] args;
	private String token;
	private String loginKey;
	private boolean loginVeify = false;
	private String authKey;
	private boolean authVeify = false;
	private String uri;
	private String ip;
	private TokenUser loginUser;

}
