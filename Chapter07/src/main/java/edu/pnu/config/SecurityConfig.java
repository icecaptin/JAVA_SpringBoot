package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

		security.authorizeHttpRequests(auth -> {
//			auth.requestMatchers("/").permitAll();
//			auth.requestMatchers("/memnber/**").authenticated();
//			auth.requestMatchers("/manager/**").authenticated();
//			auth.requestMatchers("/admin/**").authenticated();
			auth.requestMatchers("/").permitAll()
			.requestMatchers("/member/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")//;
			.anyRequest().permitAll();
		});
		
		security.csrf().disable();
//		security.csrf(csrf->csrf.disable());
		
		security.formLogin(form->{
			form.loginPage("/login")
			.defaultSuccessUrl("/loginSuccess", true);
		});
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		security.logout(log->{
			log.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login");
		});
		
		security.userDetailsService(boardUserDetailsService);

		return security.build();
	}
	
	 @Autowired
	 public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
		 .withUser("member")
		 .password("1234")
		 .roles("MEMBER");
		 
		 auth.inMemoryAuthentication()
		 .withUser("manager")
		 .password("1234")
		 .roles("MANAGER");
		 
		 auth.inMemoryAuthentication()
		 .withUser("admin")
		 .password("admin1234")
		 .roles("ADMIN");
	 }
}
