package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); 	// CSRF 보호 비활성화 (JS에서 호출 가능)
        http.cors(cors -> cors.disable());	// CORS 보호 비활성화 (React에서 호출 가능) : RestAPI로 호출할 때

        http.authorizeHttpRequests(security -> {
            security.requestMatchers("/member/**").authenticated()
                    .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/join").permitAll() // /join 경로를 허용
                    .anyRequest().permitAll();
        });


        http.formLogin(frmLogin -> {
            frmLogin.loginPage("/login")
                    .defaultSuccessUrl("/loginSuccess", true);
        });
        
        http.exceptionHandling(ex-> ex.accessDeniedPage("/accessDenied"));
        http.logout(logt -> {
        	logt.invalidateHttpSession(true)
        	.deleteCookies("JSESSIONID")
        	.logoutSuccessUrl("/login");
        });

        return http.build();
    }
}

