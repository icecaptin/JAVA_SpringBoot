//package edu.pnu;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import edu.pnu.domain.Board;
//import edu.pnu.domain.Member;
//import edu.pnu.persistence.BoardRepository;
//import edu.pnu.persistence.MemberRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DataIniTest {
//	@Autowired
//	private MemberRepository memberRepo;
//	@Autowired
//	private BoardRepository boardRepo;
//	
//	@Test
//	public void testDataInsert() {
//		Member member1 = new Member();
//		member1.setId("member1");
//		member1.setName("둘리");
//		member1.setPassword("member111");
//		member1.setRole("ROLE_USER");
//		memberRepo.save(member1);
//		
//		Member member2 = new Member();
//		member2.setId("member2");
//		member2.setName("양성부");
//		member2.setPassword("member222");
//		member2.setRole("ROLE_ADMIN");
//		memberRepo.save(member2);
//		
//		for (int i = 1; i <= 3; i++ ) {
//			Board board = new Board();
//			board.setWriter("둘리");
//			board.setTitle("둘리가 등록한 게시글 " + i);
//			board.setContent("둘리가 게시한 내용" + i);
//		}
//		
//		for (int i = 1; i <= 3; i++ ) {
//			Board board = new Board();
//			board.setWriter("양성부");
//			board.setTitle("양성부가 등록한 게시글 " + i);
//			board.setContent("양성부가 게시한 내용" + i);
//		}
//	}
//	
//	
//}
