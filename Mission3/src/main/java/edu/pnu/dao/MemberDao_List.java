package edu.pnu.dao;

import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDao_List implements MemberInterface {

    private List<MemberVO> members;

    public MemberDao_List() {
        this.members = new ArrayList<>();
    }

    @Override
    public List<MemberVO> getMembers() {
        return members;
    }

    @Override
    public MemberVO getMember(Integer id) {
        for (MemberVO member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
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
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(member.getId())) {
                members.set(i, member);
                return member;
            }
        }
        return null;
    }

    @Override
    public int deleteMember(Integer id) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(id)) {
                members.remove(i);
                return 1;
            }
        }
        return 0;
    }

    // 추가한 메소드
    @Override
    public MemberVO getMember(Long id) {
        for (MemberVO member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
}
