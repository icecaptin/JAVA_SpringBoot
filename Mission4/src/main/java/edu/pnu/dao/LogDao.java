package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LogDao {
    private String driver = "org.h2.Driver";
    private String url = "jdbc:h2:tcp://localhost/~/chapter3";
    private String username = "yang";
    private String password = "1234";

    private Connection con;

    public LogDao() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLog(String method, String sqlString, boolean success) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO dblog (method, sqlstring) VALUES ('" +
                    method + "', '" +
                    sqlString + "')");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("로그가 추가되었습니다.");
    }
}
