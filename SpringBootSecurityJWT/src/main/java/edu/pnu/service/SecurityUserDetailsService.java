package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 주어진 사용자명으로 회원 정보를 조회
		Member member = memRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException("Not Found!"));

		// 회원 정보를 기반으로 UserDetails 객체를 생성하여 반환
		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
	}
}
