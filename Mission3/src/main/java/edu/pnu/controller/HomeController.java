package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello: " + name;
	}
	
	public HomeController() {
		System.out.println("HomeController 가 생성되었습니다");
		log.info("HomeController info 가 생성되엇습니다");
		log.error("HomeController error 가 생성되엇습니다");
		log.warn("HomeController warn 가 생성되엇습니다");
		log.trace("HomeController trace 가 생성되엇습니다");
		log.debug("HomeController debug 가 생성되엇습니다");
	}
}