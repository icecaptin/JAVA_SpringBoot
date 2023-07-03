package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String registerUser(Member member) {
	    // 사용자 정보를 DB에 저장
	    member.setUsername(member.getUsername());
	    member.setPassword(encoder.encode(member.getPassword()));
	    member.setRole("ROLE_MEMBER");
	    member.setEnabled(true);
	    memberService.save(member);

	    return "welcome";
	}

}
