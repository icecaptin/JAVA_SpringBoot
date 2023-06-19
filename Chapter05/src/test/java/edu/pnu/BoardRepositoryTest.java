package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;

	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("등록됨?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);
	}
	
	@Test
	@Order(1)
	public void testInsertBoard0to10() {
		for (int i = 0; i <= 5; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					.writer("writer" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt(0L)
					.build());
		}
	}
	
	@Test
	public void testGetboard() {
		
//		Optional<Board> opt = boardRepo.findById(1L);
//		Board board = opt.get();
	
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}
	
	@Test
	public void testUpdateboard() {
		
		System.out.println("-수정 전-");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("-수정 -");
		board.setTitle("수정된 제목");
		boardRepo.save(board);
	}

	@Test
	public void testDeleteboard() {
		boardRepo.deleteById(4L);
	}
	
	@Test
	public void testfintAll() {
		List<Board> list = boardRepo.findAll();
		for (Board b : list) {
			System.out.println(b);
		}
	}
}
