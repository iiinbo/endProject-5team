package com.baemin.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exceptiontroller implements ErrorController {
	
	
	@GetMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		
		System.out.println("에러");
		
		String errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		
		model.addAttribute("errorCode", errorCode);
		
		return "error/errorPage";
	}

}
