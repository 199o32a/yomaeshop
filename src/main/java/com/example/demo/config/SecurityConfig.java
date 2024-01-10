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
                authorizeRequests.requestMatchers("/user/**").authenticated();
                authorizeRequests.requestMatchers("/manager/**")
                        .hasAnyRole("ADMIN", "MANAGER");
                authorizeRequests.requestMatchers("/admin/**")
                        .hasRole("ADMIN");
                        
                authorizeRequests.anyRequest().permitAll();
            })
			.formLogin((formLogin) -> {
				formLogin
				.loginPage("/login")
				.defaultSuccessUrl("/",true)
				.permitAll();
			})
			.logout(logout -> logout.permitAll());
			return http.build();
				
	}
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	
}
