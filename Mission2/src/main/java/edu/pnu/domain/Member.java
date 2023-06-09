package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter //데이터 읽어왔을때 객체로 가져오기 위한 클래스. 게터와 세터 제공해야.
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Member { 	
	private Long id;
	private String pass;
	private String name;
	private Date regidate;
}
