package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

//@Runwith 이건 사용 안함
@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class BoardRepositoryTest {

	@Autowired

	private BoardRepository boardRepo;

//	@Test	
//	void contextLoads() {
//		Board board = new Board();
//		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
//		board.setContent("잘 등록되나요?");
//		board.setCreateDate(new Date());
//		board.setCnt(0L);
//
//		boardRepo.save(board);
//	}

//	@Test
//	void testInsertBoard() {
//		for (int i = 0; i < 10; i++) {
//			boardRepo.save(Board.builder()
//					.title("title" + i)
//					.writer("writer" + i)
//					.content("content" + i)
//					.createDate(new Date())
//					.cnt(0L).build());
//		}
//	}

//	@Test
	public void testGetBoard() {

//		Optional<Board> opt = boardRepo.findById(1L); //findById 옵셔널이 get
//		Board board = opt.get(); 이거 두줄이 아래 한줄.
//		
		Board board = boardRepo.findById(1L).get(); // 1행 검색
		System.out.println(board);
	}

//	@Test
	public void testUpdateBoard() {
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정전");
			System.out.println(board);

			board.setTitle("제목 수정");
			boardRepo.save(board);

		}
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board);
		}
	}

//	@Test
//	public void testDeleteBoard() {
//		boardRepo.deleteById(2L); //테이블 2행 삭제
//	}

//	@Test
	public void testFindAll() {
		List<Board> list = boardRepo.findAll(); // findAll(); 까지만 하면 얘가 뭔가 리턴해줘야 할게 있는데 어떻게? 마우스 얹져봐.
		System.out.println("-------------------------------");
		System.out.println("모든 데이터를 출력합니다.");
		for (Board b : list) {
			System.out.println(b);
		}

	}
	
//	@Test
//	public void testByTitleLike(String title) {
//		System.out.println("-".repeat(80));
//		List<Board> list = boardRepo.findByTitleLike("1"); //페이징에 걸린거 말고 위에걸로 해보셈
//		System.out.println("타이틀이 1이 포함된 데이터 출력");
//		for (Board b : list) {
//			System.out.println(b);
//		}
//		System.out.println("-".repeat(80));
//	}
	
	
	
	
	
	
	

	// --------------------

//	@Test // test만 골뱅이 해주면 실행 안한다.
	public void testQueryAnnotationTest1() {
		// select b from Board b where b.title like '%title1' order by b.seq desc
		// 이런 쿼리가 날라가는거랑 똑같은 효과
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test // test만 골뱅이 해주면 실행 안한다.
	public void testQueryAnnotationTest2() {		
		List<Board> list = boardRepo.testQueryAnnotationTest2("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test // test만 골뱅이 해주면 실행 안한다.
	public void testQueryAnnotationTest3() {	
		List<Object[]> list = boardRepo.queryAnnotationTest3("title1");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
//	@Test // test만 골뱅이 해주면 실행 안한다.
	public void testQueryAnnotationTest4() {	
		List<Object[]> list = boardRepo.queryAnnotationTest4("title1");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	@Test
	public void testQueryAnnotationTest5() { //스태틱펑션. 페이지번호, 레코드 카운트, 출력할 sort.
		//어라 근데 쿼리에서 asc로 정렬 했는데? 그럼 누가 더 쎄? PageRequest 컨트롤 클릭으로 보기
		//오름차순으로. 3개씩 0페이지 잘라서 가져왔네
		Pageable paging = PageRequest.of(5, 5); //쿼리에 order by 해놓으면 밑에처럼 소팅 필요가없네. 이렇게 하면 돼
//		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq"); //seq 대신 cnt로도 해보자
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		for (Board b : list) {
			System.out.println("--->"+b);
		}
	}
}
