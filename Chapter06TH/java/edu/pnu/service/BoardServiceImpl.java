package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service // IOC 컨테이너에 오려야지
public class BoardServiceImpl implements BoardService { // 인터페이스를 해줄랑가봉가

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get(); //.get() 해주면 안의 데이터 타입으로 바꿔서 리턴해준다
		//findById가 옵셔널 타입이야. .get()으로 타입 바꿔줘야 빨간줄 없어져
	}

	@Override
	public void updateBoard(Board board) {

	}

	@Override
	public void deleteBoard(Board board) {

	}
}