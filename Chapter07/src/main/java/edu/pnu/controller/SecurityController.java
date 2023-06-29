package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}
	
	@GetMapping("/member")
	public void forMember() {
		System.out.println("member 요청입니다.");
	}
	
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("manager 요청입니다.");
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("admin 요청입니다.");
	}
	
}
