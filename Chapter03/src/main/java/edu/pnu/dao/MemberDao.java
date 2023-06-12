package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/chapter3";
	private String username = "yang";
	private String password = "1234";
	
	private Connection con;
	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database Connected");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
			rs.next();
			Member m = Member.builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.age(rs.getInt("age"))
					.nickname(rs.getString("nickname"))
					.build();
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertMember(Member m) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("insert into Member (name, age, nickname) values('%s', %d, '%s')",
					m.getName(), m.getAge(), m.getNickname()));
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("입력되었습니다.");
	}

	
	public List<Member> getMembers() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member"));
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
						.build();
				members.add(m);
			}
			rs.close();
			stmt.close();
			return members;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
