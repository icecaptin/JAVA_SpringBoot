package edu.pnu.service;

import edu.pnu.domain.Member;

public interface MemberService {
    Member getMember(Member member);
    
    void addMember(Member member);
}

