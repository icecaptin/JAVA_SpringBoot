package edu.pnu;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynimicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	private void test(String searchCondition, String searchKeyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			// select b from Board b where b.title like '%' || :searchKeyword|| '%'
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		}else if (searchCondition.equals("CONTENT")) {
			// select b from Board b where b.content like '%' || :searchKeyword|| '%'
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		}
		
		Iterable<Board> list = boardRepo.findAll(builder);
		for(Board b : list) {
			System.out.println("--->" + b);
		}
	}
	
	private void test1(Map<String, String> map) {
	    BooleanBuilder builder = new BooleanBuilder();
	    QBoard qboard = QBoard.board;
	    
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	        String searchCondition = entry.getKey();
	        String searchKeyword = entry.getValue();
	        
	        if (searchCondition.equals("TITLE")) {
	            builder.or(qboard.title.like("%" + searchKeyword + "%"));
	        } else if (searchCondition.equals("CONTENT")) {
	            builder.or(qboard.content.like("%" + searchKeyword + "%"));
	        }
	    }
	    
	    Iterable<Board> list = boardRepo.findAll(builder);
	    for (Board b : list) {
	        System.out.println("--->" + b);
	    }
	}
	
	private void test2(Map<String, String> map) {
	    Random random = new Random();
	    
	    for (int i = 0; i < 100; i++) {
	        Board board = new Board();
	        
	        board.setTitle("Title " + i);
	        board.setContent("Content " + i);
	        board.setCnt2(random.nextInt(100)); //랜덤숫자
	        
	        boardRepo.save(board);
	    }
	}


	@Test
	public void testDynamicQuery() {
//		test("TITLE", "title1");
//		test("CONTENT", "content2");
		
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content2");
		test1(map);
	}
	
	public void testDynamicQuery2() {
//		test("TITLE", "title1");
//		test("CONTENT", "content2");
		
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content2");
		test2(map);
	}
}
