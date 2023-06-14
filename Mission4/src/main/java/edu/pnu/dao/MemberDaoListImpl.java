package edu.pnu.dao;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {

    private List<MemberVO> members;

    public MemberDaoListImpl() {
    	members = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
        	members.add(new MemberVO(i, "1234", "이름" + i, new Date()));
        }
    }

    @Override
    public List<MemberVO> getMembers() {
        return members;
    }

    @Override
    public MemberVO getMember(Integer id) {
		for (MemberVO m : members) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}

    @Override
    public MemberVO addMember(MemberVO member) {
        members.add(member);
        return member;
    }

    @Override
	public MemberVO updateMember(MemberVO member) {
		for (MemberVO m : members) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}

	@Override
	public int deleteMember(Integer id) {
		for (MemberVO m : members) {
			if (m.getId() == id) {
				members.remove(m);
				return 1;
			}
		}
		return -1;
	}
}
