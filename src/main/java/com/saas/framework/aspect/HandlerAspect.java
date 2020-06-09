package com.saas.framework.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.saas.framework.aspect.handler.HandlerManager;
import com.saas.framework.aspect.params.AnnotationParams;
import com.saas.framework.aspect.params.HandlerParamters;
import com.saas.framework.aspect.process.Processer;

@Aspect
@Component
public class HandlerAspect {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HandlerManager handlerManager;

	@Autowired
	Processer processer;

	@Around(value = "@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
	public Object userInject(ProceedingJoinPoint point) throws Throwable {
//		ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = atts.getRequest();
//		HttpServletResponse response = atts.getResponse();
//		String uri = request.getRequestURI();
//		if (uri.contains("api/"))
//			return point.proceed();

		ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = atts.getRequest();
		HttpServletResponse response = atts.getResponse();
		
		HandlerParamters params = processer.process(point, request, response);
//		AnnotationParams annos = params.getAnnos();
		
		
		Object result = handlerManager.aroundHandler(params);
		if (result != null)
			return result;

		return point.proceed();
	}
	
	
//	private boolean verifyLoginAuth(AnnotationParams annos) {
//		boolean isVerify = false;
//		if(annos.isVerifyAuth())
//			return true;
//		
//		
//		
//		return isVerify;
//	}

}
