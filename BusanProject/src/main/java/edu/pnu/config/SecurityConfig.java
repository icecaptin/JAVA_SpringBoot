package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
//    	http.csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화 (JS에서 호출 가능)
        http.cors(cors -> cors.disable()); // CORS 보호 비활성화 (React에서 호출 가능) : RestAPI로 호출할 때
        return http.build();
    }
}
