package com.saas.framework.aspect.bak;
//package com.saas.framework.aspect.handler;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.saas.framework.aspect.IRestHandler;
//import com.saas.framework.utils.JsonUtils;
//
//@Component
//public class LoggerRestHandlerBak implements IRestHandler {
//
//	Logger LOGGER = LoggerFactory.getLogger(LoggerRestHandlerBak.class);
//
////	private LoggerRestHandler() {
////		handlers.add(this);
////		if (handlers.size() == HANDLER_SIZE) {
////			Collections.sort(handlers, compare);
////		}
////	}
//
//	@Override
//	public Integer compare() {
//		return 4;
//	}
//
//	@Override
//	public void beforeHandler() {
//
//	}
//
//	@Override
//	public Object aroundHandler(HandlerParams params) {
//		this.printLog(params);
//		return null;
//	}
//
//	private void printLog(HandlerParams params) {
//		String method = params.getMethodName();
//		String clazz = params.getClassName();
//		Object[] args = params.getArgs();
//		if (args != null && args.length > 0) {
//			LOGGER.debug("方法：{}.{}，参数：{}", clazz, method, JsonUtils.toJsonString(args[0]));
//		}
//	}
//
//}
