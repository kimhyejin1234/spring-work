package com.spring.myweb.snsboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;

@RestController //이걸 사용하려면 , ModelAndView 를 사용해야 화면이 보인다.
@RequestMapping("/snsboard")
@RequiredArgsConstructor
public class SnsBoardController {
	
	private  final SnsBoardService service;
	
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("name","value"); data 를 담고 싶을때
		mv.setViewName("snsboard/snsList");
		return mv;
	}
	
}
