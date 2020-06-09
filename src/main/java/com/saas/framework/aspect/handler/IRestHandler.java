package com.saas.framework.aspect.handler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.saas.framework.aspect.params.HandlerParamters;

public interface IRestHandler {

	Integer HANDLER_SIZE = 3;

	List<IRestHandler> handlers = new ArrayList<IRestHandler>();

	Comparator<IRestHandler> compare = new Comparator<IRestHandler>() {
		@Override
		public int compare(IRestHandler o1, IRestHandler o2) {
			return o1.compare() - o2.compare();
		}
	};

	Integer compare();

	void beforeHandler();

	Object aroundHandler(HandlerParamters params);

}
