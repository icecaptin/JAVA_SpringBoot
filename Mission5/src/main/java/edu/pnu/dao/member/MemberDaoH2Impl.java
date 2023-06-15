package edu.pnu.dao.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {
	private Connection con = null;
	
	public MemberDaoH2Impl() {
        try {
            // JDBC 드라이버 로드
            Class.forName("org.h2.Driver");
            
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/chapter3", "yang", "1234");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
	}
	
	@Override
	public Map<String, Object> getMembers() {
	    Statement st = null;
	    ResultSet rs = null;
	    String sqlString = "select id, name, age, nickname, regidate from member order by id asc";
	    try {
	        List<MemberVO> list = new ArrayList<>();
	        st = con.createStatement();
	        rs = st.executeQuery(sqlString);
	        while (rs.next()) {
	            MemberVO m = new MemberVO(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
	                    rs.getString("nickname"), rs.getDate("regidate"));

	            list.add(m);
	        }
	        Map<String, Object> map = new HashMap<>();
	        map.put("sql", sqlString);
	        map.put("data", list);
	        return map;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        String sqlString = String.format("select id, name, age, nickname, regidate from member where id=%d", id);
	        st = con.createStatement();
	        rs = st.executeQuery(sqlString);
	        rs.next();
	        MemberVO m = new MemberVO(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
	                rs.getString("nickname"), rs.getDate("regidate"));
	        Map<String, Object> map = new HashMap<>();
	        map.put("sql", sqlString);
	        map.put("data", m);
	        return map;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}

	
	private int getNextId() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
			return rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;		
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		
		int id = getNextId();
		
		Statement st = null;
		try {
			String sqlString = String.format("insert into member (id,name,age,nickname) values (%d,'%s', %d,'%s')",
				id, member.getName(), member.getAge(), member.getNickname());
			st = con.createStatement();

			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(id);
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		Statement st = null;
		try {
			String sqlString = String.format("update member set name='%s',age='%s' where id=%d",
					member.getName(), member.getAge(), member.getId());
			st = con.createStatement();
			
			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				Map<String, Object> map = getMember(member.getId());
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		Statement st = null;
		try {
			String sqlString = String.format("delete from member where id=%d", id);
			st = con.createStatement();

			Map<String, Object> map = getMember(id);
			if (map.get("data") == null) 
				return null;

			Map<String, Object> ret = new HashMap<>();
			if (st.executeUpdate(sqlString) == 1) {
				ret.put("sql", sqlString);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("sql", sqlString);
				ret.put("data", null);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
