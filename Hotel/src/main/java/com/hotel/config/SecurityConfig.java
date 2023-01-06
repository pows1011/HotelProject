package com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			//HTTTP ServletRequest를 사용하는 요청들에 대한 접근 제한 설정,
		http.authorizeHttpRequests().requestMatchers("/").permitAll() // 해당 주소로 시작하는 값은 모든 이용자가 접근가능
			.requestMatchers("/mypage")	// mypage 하위는 user인증을 받은 사람만 접근
			.hasRole("USER")
			.requestMatchers("/message")
			.hasRole("MANAGER")
			.requestMatchers("/adsd")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated();		
		http.formLogin().loginPage("/member/login").defaultSuccessUrl("/index").failureUrl("/member/login")
				.usernameParameter("m_id").passwordParameter("m_pwd").loginProcessingUrl("/index");
		
		http.exceptionHandling() //401,403 Exception에 대한 핸들링,
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//세션의 사용을 금지,
			
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		String password = passwordEncoder.encode("1234");
		manager.createUser(User.withUsername("user").password(password).roles("USER").build());
		manager.createUser(User.withUsername("manager").password(password).roles("MANAGER").build());
		manager.createUser(User.withUsername("admin").password(password).roles("ADMIN").build());

		return manager;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { // css,js,img등의 시큐리티 필터적용이 필요없는 자원에 대한 접근을 설정,
		return (web) -> web.ignoring().requestMatchers("/css/**","/font/**","/img/**");
		//requestMatcher.permitAll과 비슷하지만 permitAll은 Security필터에서 한번 검증을 받고 넘어가고 webignoring은 아예 필터를 거치지 않고 통과.
	}

}
