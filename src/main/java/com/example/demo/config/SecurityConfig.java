package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((authorizeRequests) -> {
                authorizeRequests.requestMatchers("/security-login/info").authenticated();
                authorizeRequests.requestMatchers("/security-login/admin/**")
                        .hasRole("ADMIN");
                        
                authorizeRequests.anyRequest().permitAll();
            })
			.formLogin((formLogin) -> {
				formLogin
				.usernameParameter("loginid")
				.passwordParameter("pw")
				.loginPage("/security-login/login")
				.defaultSuccessUrl("/security-login")
				.failureUrl("/security-login/login")
				.permitAll();
			})
			.logout(logout -> 
			logout
					.logoutUrl("/security-login/logout")
					.invalidateHttpSession(true).deleteCookies("JSESSIONID"));
			return http.build();
				
	}
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	
}
