package edu.pnu.domain;

import java.util.Date;

public class MemberVO {
    private Integer id;
    private String pass;
    private String name;
    private Date regidate;

    public MemberVO() {
    }

    public MemberVO(Integer id, String pass, String name, Date regidate) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.regidate = regidate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    @Override
    public String toString() {
        return "MemberVO [id=" + id + ", pass=" + pass + ", name=" + name + ", regidate=" + regidate + "]";
    }
}
