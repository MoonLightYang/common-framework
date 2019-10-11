package com.saas.framework.aspect;

import java.util.ArrayList;
import java.util.List;

import com.saas.framework.aspect.handler.HandlerParams;

public interface IRestHandler {

	void beforeHandler();

	Object aroundHandler(HandlerParams params);

	List<IRestHandler> handlers = new ArrayList<IRestHandler>();

}
