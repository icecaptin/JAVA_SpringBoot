package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.TestService1;
import edu.pnu.service.TestService2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //4번방법
public class TestController {
	
	//1번방법. 필드
	
//	@Autowired
//	private TestService1 testService1;
//	
//	@Autowired
//	private TestService2 testService2;
//	
//	public TestController() {
//		System.out.println("TestController");
//	}
	
	//2번방법. 생성자
	private final TestService1 test1; //4번방법
	private final TestService2 test2;
	
//	@Autowired
//	public TestController(TestService1 test1, TestService2 test2) {
//		this.test1 = test1;
//		this.test2 = test2;
//		
//		System.out.println("TestController");
//	}
	
	
	@GetMapping({"/", "home"}) //그냥	
	public String home() {
		return "Home";
	}
	
	@GetMapping({"/test"}) //그냥	
	public String test() {
		return test1.test();
	}
}
