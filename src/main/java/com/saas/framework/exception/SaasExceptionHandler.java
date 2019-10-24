package com.saas.framework.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.saas.framework.RestEntity;

@RestControllerAdvice
public class SaasExceptionHandler {

	@ExceptionHandler(value = BindException.class)
	public RestEntity handleBindException(BindException ex) {
		ex.printStackTrace();
		List<String> infos = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		return RestEntity.FAIL().info(infos.get(0));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public RestEntity handleBindGetException(ConstraintViolationException ex) {
		ex.printStackTrace();
		List<String> infos = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
		return RestEntity.FAIL().info(infos.get(0));
	}

	@ExceptionHandler(value = SaasException.class)
	public RestEntity handleException(SaasException ex) {
		ex.printStackTrace();
		String error = ex.getMessage();
		return RestEntity.FAIL().info(error);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public RestEntity handleException(HttpMessageNotReadableException causeEx) {
		causeEx.printStackTrace();
		String error = "传入参数有误";
		Throwable ex = causeEx.getCause();
		if (ex instanceof InvalidFormatException)
			error = "参数值或类型不匹配：" + ((InvalidFormatException) ex).getValue();

		return RestEntity.ERROR().info(error);
	}

	@ExceptionHandler(value = Exception.class)
	public RestEntity handleException(Exception ex) {
		ex.printStackTrace();
		String error = ex.getMessage();
		return RestEntity.ERROR().info(error);
	}

}

//else if (ex instanceof HttpRequestMethodNotSupportedException) 
//	System.out.println("1");
//else if (ex instanceof HttpMediaTypeNotSupportedException) 
//	System.out.println("2");
//else if (ex instanceof HttpMediaTypeNotAcceptableException) 
//	System.out.println("3");
//else if (ex instanceof MissingPathVariableException) 
//	System.out.println("4");
//else if (ex instanceof MissingServletRequestParameterException) 
//	System.out.println("5");
//else if (ex instanceof ServletRequestBindingException) 
//	System.out.println("6");
//else if (ex instanceof ConversionNotSupportedException) 
//	System.out.println("7");
//else if (ex instanceof TypeMismatchException) 
//	System.out.println("8");
//else if (ex instanceof HttpMessageNotReadableException) 
//	System.out.println("9");
//else if (ex instanceof HttpMessageNotWritableException) 
//	System.out.println("10");
//else if (ex instanceof MethodArgumentNotValidException) 
//	System.out.println("11");
//else if (ex instanceof MissingServletRequestPartException) 
//	System.out.println("12");
//else if (ex instanceof BindException) 
//	System.out.println("13");
//else if (ex instanceof NoHandlerFoundException) 
//	System.out.println("14");
