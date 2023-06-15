package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member") // 매핑 URL의 공통 경로를 설정합니다.
@Service
public class MemberController {
	//필드에서 설정하는 방법
	@Autowired
	private final MemberService memberService;

	@Autowired //생성자에서 설정하는 방법
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
//	    this.memberService = memberService;
//	    log.info("MemberController가 생성되었습니다.");
	}
	
	@GetMapping("/{id}")
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}

	@GetMapping
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	@PostMapping
	public void insertMember(@RequestBody Member member) {
		memberService.insertMember(member);
	}

	@PutMapping
	public void updateMember(@RequestBody Member member) {
		memberService.updateMember(member);
	}

	@DeleteMapping("/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.deleteMember(id);
	}
	
}
