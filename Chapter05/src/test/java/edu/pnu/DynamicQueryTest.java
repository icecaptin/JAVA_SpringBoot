package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;

	private void test(String searchCondition, String searchKeyword) {
		BooleanBuilder builder = new BooleanBuilder(); // 얘가 쿼리 누적하는 애
		QBoard qboard = QBoard.board; //@Service 하니까 이게 오류나네?
//		Pageable paging = PageRequest.of(5, 5);
		
		// 조건 걸 매개체. 골뱅이 엔티티 붙은 애들에서 자동으로. 자동으로 QBoard클래스 만들어준다.

		if (searchCondition.equals("TITLE")) {
			// select b from Board b where b.title like '%'||:searchKeyword||'%'
			// 이 쿼리랑 똑같은 쿠리가 날라간다.
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			// select b from Board b where b.content like '%'||:searchKeyword||'%'
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
			
		} else if (searchCondition.equals("CNT")) {
			// select b from Board b where b.content like '%'||:searchKeyword||'%'
			builder.and(qboard.cnt.gt(50));
		}	
		
		Iterable<Board> list = boardRepo.findAll(builder/*,paging*/);
		for (Board b : list) {
			System.out.println("--->" + b);
		}
	}

	private void test1(Map<String, String> map) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;

		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (key.equals("TITLE")) {
				builder.and(qboard.title.like("%" + map.get(key) + "%"));
			} else if (key.equals("CONTENT")) {
				builder.and(qboard.content.like("%" + map.get(key) + "%"));
				// or 조건으로 바꾸고 싶으면 builder.or 하면 된다.
			}
		}
	}

//	@Test
	public void testDynamicQuery() {
//		test("Title", "title1"); // Title 대신 title1 넣어 검색~ 이런식. 책이랑 좀 다르게.
//		test("CONTENT", "content2"); //위에 있던 테스트 아래로 내렸다.
//		test2("Title", "title1", "CONTENT", "content2");//이거 하는건 위에.
		// 이거 해보려고 위에 test(String str,...) 이거 할랬는데 기억 안나서 아래걸로 대체 하심. Map으로
		// 아래로 해봐. 일단 해놓긴 했는데 잘 설정했는지 해보셈.

		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content2");
		test1(map);
	}
	
	private void test3(String searchCondition, String searchKeyword, Pageable paging) {
		BooleanBuilder builder = new BooleanBuilder(); 
		QBoard qboard = QBoard.board;		

		if (searchCondition.equals("TITLE")) {		
			builder.and(qboard.title.like("%" + searchKeyword + "%"));	
			
		} else if (searchCondition.equals("CNT")) {			
			builder.and(qboard.cnt.gt(50));
		}	
		
		Iterable<Board> list = boardRepo.findAll(builder, paging);
		for (Board b : list) {
			System.out.println("--->" + b);
		}
	}
	
	
	

//	@Test
	public void queryAnnotationTest1() {
		test("TITLE", "title1");
	}

//	@Test
	public void queryAnnotationTest2() {
		test("CNT", "50");
	}	
	
	@Test
	public void testQueryAnnotationTest3() {
		Pageable paging = PageRequest.of(0, 5);
		test3("TITLE", "title1", paging);		
	}
	
//	@Test
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(5, 5);
		test3("CNT", "50", paging);		

	}

}
