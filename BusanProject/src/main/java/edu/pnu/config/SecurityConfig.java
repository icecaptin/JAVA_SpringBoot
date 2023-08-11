package edu.pnu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.pnu.repository.BusanUserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationConfiguration authConfig;
	private final BusanUserRepository busanUserRepository;
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(security -> security
	               .requestMatchers("/comment/add").authenticated()
	               .requestMatchers("/comment/**").permitAll()
	               .requestMatchers("/admin/**").hasRole("ADMIN")
	               .anyRequest().permitAll());
		

//		http.authorizeHttpRequests(security -> security
//			    .requestMatchers("/comment/**").permitAll() // 댓글 경로에 대해서는 누구나 접근 허용
//			    .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 경로에 대해서는 ADMIN 역할이 있는 사용자만 접근 허용
//			    .anyRequest().permitAll() // 나머지 모든 요청에 대해서는 누구나 접근 허용
//			);



		http.formLogin(frmLogin -> frmLogin.disable()); // 폼 로그인 기반 비활성화
		http.sessionManagement(ssmg -> ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 없애기. jwt 할거.
		http.addFilterBefore(new JWTAuthorizationFilter(busanUserRepository, secretKey), UsernamePasswordAuthenticationFilter.class); // 권한검사
		return http.build();
	}
}
