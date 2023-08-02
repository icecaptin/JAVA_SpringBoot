package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.service.BusanUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private BusanUserService busanUserService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder를 빈으로 등록하여 비밀번호 인코딩에 사용한다.
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(security -> {
			security.requestMatchers("/**")
					.permitAll();
		});
        http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		//커밋테스트
		
		return http.build();
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(busanUserService).passwordEncoder(passwordEncoder());
//    }
}
