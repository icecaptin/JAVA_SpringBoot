package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {
	@Autowired
	BoardRepository boardRepo;

	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}

	// create
	@PostMapping("/insertBoard")
	public Board insertBoard(Board board) {
		if (board.getCreateDate() == null)
			board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	// create
		@PostMapping("/insertBoardjson")
		public Board insertJsonBoard(@RequestBody Board board) {
			if (board.getCreateDate() == null)
				board.setCreateDate(new Date());
			return boardRepo.save(board);
		}

	// read
	@GetMapping("/board")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}

	// update
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
	}

	// delete
	@DeleteMapping("/board")
	public String deleteBoard(Long id) {
		boardRepo.deleteById(id);
		return String.format("seq가 %d 인 데이터가 삭제되엇습니다.", id);
	}
}
