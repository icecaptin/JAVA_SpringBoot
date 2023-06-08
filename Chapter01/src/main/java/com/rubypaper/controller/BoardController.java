package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
@Controller
public class BoardController {
	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController가 생성되엇습니다");
		System.out.println("=".repeat(50));
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	@PostMapping("/hello")
	public String Posthello(String name) {
		return "Posthello : " + name;
	}
	@PutMapping("/hello")
	public String Puthello(String name) {
		return "Puthello : " + name;
	}
	@DeleteMapping("/hello")
	public String Deletehello(String name) {
		return "Deletehello : " + name;
	}
	
	@GetMapping("/getBoard1")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다...............");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}
	
	@GetMapping("/getBoard2")
	public BoardVO getBoard2() {
		BoardVO board = new BoardVO(
				1,
				"테스트 제목",
				"테스터",
				"테스트 내용입니다..............",
				new Date(),
				0);
			return board;
	}
	
	@GetMapping("/getBoard3")
	public BoardVO getBoard3() {
			return BoardVO.builder()
					.seq(1)
					.title("테스트 제목")
					.writer("테스터")
					.content("테스트 내용입니다............")
					.createDate(new Date())
					.cnt(0)
					.build();
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		for (int i = 1; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(1);
			board.setTitle("테스트 제목");
			board.setWriter("테스터");
			board.setContent("테스트 내용입니다..........");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}
	
	@GetMapping("/board")
	public @ResponseBody BoardVO boardreq(@RequestBody BoardVO b) {
		b.setCreateDate(new Date());
		System.out.println("Board: " + b);
		return b;
	}
}
