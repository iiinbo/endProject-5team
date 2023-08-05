package com.baemin.error;

import javax.el.MethodNotFoundException;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Error {

	@ExceptionHandler(MethodNotFoundException.class)
	public String err(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("에러페이지");
		return "error/errorPage";
	}

}
