package edu.pnu.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.exception.BoardNotFoundException;
import edu.pnu.exception.UsernameNotFoundException;

@Controller
public class ExceptionController {

	@RequestMapping("/boardError")
	public String boardError() {
		throw new BoardNotFoundException("검색된 게시글이 없습니다");
	}

	@RequestMapping("/illegalArgumentError")
	public String ellegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자가 전달됫습니다");
	}

	@RequestMapping("/sqlError")
	public String sqlError() throws SQLException {
		throw new SQLException("sql 구문에 오류가 잇습니다");
	}

	@ExceptionHandler(SQLException.class)
	public String numberFormatError(SQLException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/sqlError";
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public String usernameNotFoundError(UsernameNotFoundException exception, Model model) {
	    model.addAttribute("exception", exception);
	    return "/errors/usernameNotFoundError";
	}

}
