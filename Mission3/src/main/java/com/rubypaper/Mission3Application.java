package com.rubypaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mission3Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission3Application.class, args);

        // MemberService memberService = new MemberService();
        // MemberController memberController = new MemberController(memberService);
        // 위 코드를 삭제하고, Spring Boot가 자동으로 관리
    }

}
