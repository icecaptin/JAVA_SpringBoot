package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {

//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<>();		
//		for (int i = 1; i <= 10 ; i++) {
//			boardList.add(Board.builder()
//					.seq((long)i)
//					.title("title" +i)
//					.writer("writer"+i)
//					.content("content"+i)
//					.createDate(new Date())
//					.cnt(0L).build());
//		}
//		model.addAttribute("boardList", boardList);	 //키값, 밸류값. 키값을 getBoardList.jsp에서 받으니까. 키값통일	
//		return "getBoardList"; //보드에 담에서 뷰 이름에 리턴
//	}

	@Autowired
	private BoardService boardService;

	@GetMapping("/getBoardList") // 위에는 임의로 우리가 데이터를 만들었고.
	public String getBoardList(Model model) { // 서비스 객체로부터 데이터를 받아서 모델에다가 담아두는거
		model.addAttribute("boardList", boardService.getBoardList());
		boardService.getBoardList();
		return "getBoardList"; // 서비스에서 findAll로 다 가져와서. 이건 DB에서 100개 가져오겠지. 페이져블도 해봐.
	}

	@GetMapping("/insertBoard") // post 안쓰고 일반적으로 쓰는거 쓴다. post 해도돼
	public String insertBoard() {
		return "insertBoard"; // insertBoard.jsp를 호출하라고 했다. insertBoard.jsp 가봐. 뷰로 호출.
	}

	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList"; // 리다이렉트 안해주면 default가 foward url조차 바꿀라고. url에 insertBoard가 그대로 뜸.
	} // insertBoard가 url인 그대로에 새글등록하면 원래 getBoardlist 화면이 나온다. url 안바뀌고. url 일치시켜주려고
		// 리다이렉트.

	@GetMapping("/getBoard")
	// public String getBoard(Board board) { //board.seq 1개 넘어오는거다.
	public String getBoard(Long seq, Model model) { // 혹은 이렇게 seq 1개만 넘겨 받게끔 하면 된다.똑같다 위랑.
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		//jsp에선 ${board.seq} //EL표현식.
		return "getBoard"; // 이제 겟보드도 서비스
	}
}