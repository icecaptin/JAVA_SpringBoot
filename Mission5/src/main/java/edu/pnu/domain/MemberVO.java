package edu.pnu.domain;

import java.util.Date;

public class MemberVO {
	private int id;
	private String name;
	private int age;
	private String nickname;
	private Date regidate;

	public MemberVO(int id, String name, int age, String nickname, Date regidate) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.regidate = regidate;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", age=" + age + ", name=" + name + ", nickname=" + nickname + ", regidate="
				+ regidate + "]";
	}
}
