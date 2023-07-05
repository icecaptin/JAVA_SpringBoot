package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private AuthenticationConfiguration authConfig;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder를 빈으로 등록하여 비밀번호 인코딩에 사용한다.
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());

		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**").authenticated().requestMatchers("/manager/**")
					.hasAnyRole("MANAGER", "ADMIN").requestMatchers("/admin/**").hasRole("ADMIN").anyRequest()
					.permitAll();
		});

		http.formLogin(frmLogin -> frmLogin.disable()); // Form을 이용한 로그인을 사용하지 않겠다는 설정
		http.sessionManagement(ssmg -> ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 시큐리티 세션을 만들지 않겠다고 설정

		http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager()));
		return http.build();
	}
}
