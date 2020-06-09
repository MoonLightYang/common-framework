package com.saas.framework.aspect.params;

import com.saas.framework.session.SessionUser;

import lombok.Data;

@Data
public class HandlerParamters {

	private RequestParams request;
	private ProcessParams process;
	private AnnotationParams annos;
	private SessionUser session;

	public HandlerParamters(RequestParams request, ProcessParams process, AnnotationParams annos, SessionUser session) {
		this.request = request;
		this.process = process;
		this.annos = annos;
		this.session = session;
	}

}
