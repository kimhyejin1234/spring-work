package com.spring.basic.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class ReuqestController_Self {

	public ReuqestController_Self() {
		System.out.println("RequestCon-SELF 생성됨!");
	}
	
	@GetMapping("/basic01_self")
	public String setGet() {
		System.out.println("/basic01-self 요청이들어옴! : GET 방식!!");
		return "request/req-ex01-self";
	}
	
	@PostMapping("/basic01_self")
	public String setPost() {
		System.out.println("/basic01-self 요청이들어옴! : post 방식!!");
		return "request/req-ex01-self";
	}
}
