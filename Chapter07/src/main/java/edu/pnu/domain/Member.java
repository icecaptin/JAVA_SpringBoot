package edu.pnu.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	public Collection<? extends GrantedAuthority> getAuthoritites() {
		return AuthorityUtils.createAuthorityList(role);
//		Collection<GrantedAuthority> list = new ArrayList<>();
//		list.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return role;
//			}
//		});
//		return list;
	}
}
