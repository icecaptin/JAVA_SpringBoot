package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/chapter3";
	private String username = "yang";
	private String password = "1234";

	private Connection con;

	public MemberDaoH2Impl() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberVO> getMembers() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Member");
			List<MemberVO> members = new ArrayList<>();
			while (rs.next()) {
				MemberVO member = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				members.add(member);
			}
			rs.close();
			stmt.close();
			return members;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO getMember(Integer id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Member WHERE id = " + id);
			if (rs.next()) {
				MemberVO member = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				rs.close();
				stmt.close();
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Member (id, pass, name, regidate) VALUES (" +
					member.getId() + ", '" +
					member.getPass() + "', '" +
					member.getName() + "', '" +
					member.getRegidate() + "')");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("입력되었습니다.");
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE Member SET " +
					"pass = '" + member.getPass() + "', " +
					"name = '" + member.getName() + "', " +
					"regidate = '" + member.getRegidate() + "' " +
					"WHERE id = " + member.getId());
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public int deleteMember(Integer id) {
		try {
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("DELETE FROM Member WHERE id = " + id);
			stmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
