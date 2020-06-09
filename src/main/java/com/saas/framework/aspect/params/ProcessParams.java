package com.saas.framework.aspect.params;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessParams {

	private ProceedingJoinPoint point;
	private Method method;
	private String methodName;
	private String className;
	private Object[] args;

}
