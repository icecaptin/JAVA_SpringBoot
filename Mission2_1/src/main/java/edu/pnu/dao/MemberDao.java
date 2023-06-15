package edu.pnu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository
public class MemberDao {

	@Autowired
	private DataSource dataSource;

//	private String driver = "org.h2.Driver";
//	private String url = "jdbc:h2:tcp://localhost/~/mission2";
//	private String username = "yang";
//	private String password = "1234";

	private Connection con;

//	public MemberDao() {
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, username, password);
//			System.out.println("데이터베이스가 연결되었습니다");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public Member getMember(Long id) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
			rs.next();
			Member m = Member.builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
					.regidate(rs.getDate("regidate")).build();
			rs.close();
			stmt.close();
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Member> getMembers() {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member"));
			List<Member> members = new ArrayList<>();
			while (rs.next()) {
				Member m = Member.builder().id(rs.getLong("id")).pass(rs.getString("pass")).name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
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

	public void insertMember(Member member) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			stmt.executeUpdate(
					String.format("insert into Member (id, pass, name, regidate) values(%d, '%s', '%s', '%s')",
							member.getId(), member.getPass(), member.getName(), member.getRegidate()));
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("입력되었습니다.");
	}

	public void updateMember(Member member) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			stmt.executeUpdate(String.format("update Member set pass='%s', name='%s', regidate='%s' where id=%d",
					member.getPass(), member.getName(), member.getRegidate(), member.getId()));
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("업데이트되었습니다.");
	}

	public void deleteMember(Long id) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			stmt.executeUpdate(String.format("delete from Member where id=%d", id));
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("삭제되었습니다.");
	}
}
